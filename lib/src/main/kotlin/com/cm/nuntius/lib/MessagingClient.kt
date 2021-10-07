package com.cm.nuntius.lib

import com.cm.nuntius.lib.message.MessagesBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.netty.buffer.Unpooled
import kotlinx.coroutines.reactor.awaitSingle
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient

class MessagingClient(
    private val token: String,
    private val client: HttpClient = HttpClient.create()
        .baseUrl("https://gw.cmtelecom.com/v1.0")
        .headers { it.add("Content-Type", "application/json") },
    private val mapper: ObjectMapper = ObjectMapper().registerKotlinModule()
) {
    private val writer = mapper.writer().withRootName("messages")

    suspend fun sendMessage(init: MessagesBuilder.() -> Unit) {
        val messages = MessagesBuilder().apply(init).build(token)
        client.post()
            .uri("/message")
            .send(Mono.just(Unpooled.wrappedBuffer(writer.writeValueAsString(messages).toByteArray())))
            .responseSingle { _, body -> return@responseSingle body.asString() }
            .awaitSingle()
    }
}
