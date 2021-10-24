package com.cm.nuntius.lib

import com.cm.nuntius.lib.builder.message.MessagesBuilder
import com.cm.nuntius.lib.json.message.Authentication
import com.cm.nuntius.lib.json.message.Messages
import com.cm.nuntius.lib.json.request.MessageCreateRequest
import com.cm.nuntius.lib.rest.RequestHandler
import com.cm.nuntius.lib.json.response.MtCreateResponse
import com.cm.nuntius.lib.rest.RestClient
import com.cm.nuntius.lib.rest.Route

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
}
