package com.cm.nuntius.lib

import com.cm.nuntius.lib.builder.message.MessagesBuilder
import com.cm.nuntius.lib.json.message.Authentication
import com.cm.nuntius.lib.json.message.Messages
import com.cm.nuntius.lib.json.request.MessageCreateRequest
import com.cm.nuntius.lib.rest.RequestHandler
import com.cm.nuntius.lib.json.response.MtCreateResponse
import com.cm.nuntius.lib.rest.RestClient
import com.cm.nuntius.lib.rest.Route
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.request.request
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

/**
 * The MessagingClient handles making requests to the CM.com API to create messages.
 *
 * Example of usage;
 * ```
 * MessagingClient("token").sendMessage {
 *      message {
 *          reference = "1"
 *          from = "ID"
 *          to {
 *              number = "0612345678"
 *          }
 *          body {
 *              content = "Hello, World!"
 *              type = "auto"
 *          }
 *          allowedChannels(TWITTER)
 *      }
 * }
 * ```
 */
class MessagingClient(
    private val token: String
) {
    private val rest = RestClient(RequestHandler())
    private val mediaClient = HttpClient(CIO)

    /**
     * Creates the specified messages in the specified channels.
     * @param messages The messages to be created.
     * @return Returns [MtCreateResponse] on failure and success.
     */
    suspend fun sendMessage(messages: MessagesBuilder.() -> Unit): MtCreateResponse =
        rest.call(Route.MessagePost) {
            body(
                MessageCreateRequest.serializer(),
                MessageCreateRequest(Messages(Authentication(token), MessagesBuilder().apply(messages).build()))
            )
        }

    suspend fun fetchMedia(uri: String): ByteArray {
        val response = mediaClient.request<HttpResponse>(uri) {
            method = HttpMethod.Get
            header("X-CM-PRODUCTTOKEN", token)
        }

        if (response.status != HttpStatusCode.OK)
            return ByteArray(0)

        return response.content.toByteArray()
    }
}
