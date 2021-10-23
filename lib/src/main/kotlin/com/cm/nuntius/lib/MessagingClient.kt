package com.cm.nuntius.lib

import com.cm.nuntius.lib.builder.message.MessagesBuilder
import com.cm.nuntius.lib.json.message.Authentication
import com.cm.nuntius.lib.json.message.Messages
import com.cm.nuntius.lib.request.MessageCreateRequest
import com.cm.nuntius.lib.request.RequestHandler
import com.cm.nuntius.lib.response.MtCreateResponse

class MessagingClient(
    private val token: String
) {
    private val rest = RestClient(RequestHandler())

    suspend fun sendMessage(init: MessagesBuilder.() -> Unit): MtCreateResponse =
        rest.call(Route.MessagePost) {
            body(
                MessageCreateRequest.serializer(),
                MessageCreateRequest(Messages(Authentication(token), MessagesBuilder().apply(init).build()))
            )
        }
}
