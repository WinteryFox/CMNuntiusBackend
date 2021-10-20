package com.cm.nuntius.lib

import com.cm.nuntius.lib.builder.message.MessagesBuilder
import com.cm.nuntius.lib.json.message.Authentication
import com.cm.nuntius.lib.json.message.Messages
import com.cm.nuntius.lib.request.MessageCreateRequest
import com.cm.nuntius.lib.response.MtCreateResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

/**
 * The MessagingClient handles making requests to the CM.com API to create messages.
 *
 * Example of usage;
 * ```
 * MessagingClient("token").sendMessage {
 *      message {
 *          from = "ID"
 *          to {
 *              number = "0612345678"
 *          }
 *          body {
 *              content = "Hello, World!"
 *              type = "auto"
 *              reference = "1"
 *          }
 *          allowedChannels(TWITTER)
 *      }
 * }
 * ```
 */
class MessagingClient(
    private val token: String,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    },
    private val client: HttpClient = HttpClient(CIO) {
        expectSuccess = false
        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
    }
) {
    private val base = "https://gw.cmtelecom.com/v1.0"

    /**
     * Creates the specified messages in the specified channels.
     * @param messages The messages to be created.
     * @return Returns [MtCreateResponse] on success.
     */
    suspend fun sendMessage(messages: MessagesBuilder.() -> Unit): MtCreateResponse =
        client.post("$base/message") {
            body = MessageCreateRequest(Messages(Authentication(token), MessagesBuilder().apply(messages).build()))
        }
}
