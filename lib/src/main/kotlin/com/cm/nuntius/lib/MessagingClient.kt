package com.cm.nuntius.lib

import com.cm.nuntius.lib.message.MessagesBuilder
import com.cm.nuntius.lib.response.MtCreateResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.netty.buffer.Unpooled
import kotlinx.coroutines.reactor.awaitSingle
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient

class MessagingClient(
    private val token: String,
    private val client: HttpClient = HttpClient.create()
        .baseUrl("https://gw.cmtelecom.com/v1.0")
        .headers { it.add("Content-Type", "application/json") },
    private val mapper: ObjectMapper = jacksonObjectMapper()
) {
    private val writer = mapper.writer().withRootName("messages")

    suspend fun sendMessage(init: MessagesBuilder.() -> Unit): MtCreateResponse {
        val messages = MessagesBuilder().apply(init).build(token)
        return mapper.readValue(
            client.post()
                .uri("/message")
                // TODO: Not entirely sure if this is a safe call, needs some more research
                .send(Mono.just(Unpooled.wrappedBuffer(writer.writeValueAsString(messages).toByteArray())))
                .responseSingle { _, body -> body.asString() }
                .awaitSingle()
        )
    }
}
