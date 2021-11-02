package com.cm.nuntius.mt.controller

import com.cm.nuntius.lib.MessagingClient
import com.cm.nuntius.lib.json.response.MtCreateResponse
import com.cm.nuntius.mt.json.request.MessageCreateRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import java.util.*

@RestController
class MtController(
    private val client: MessagingClient
) {
    private val logger = LoggerFactory.getLogger(MtController::class.java)

    @PostMapping(
        "/messages",
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE]
    )
    suspend fun createMessage(
        exchange: ServerWebExchange,
        @RequestBody request: MessageCreateRequest
    ): MtCreateResponse {
        val response = client.sendMessage {
            message {
                reference = UUID.randomUUID().toString()
                to {
                    number = request.recipient
                }
                from = request.sender
                body {
                    content = request.content
                }
                allowedChannels(request.channel)
            }
        }

        if (response.errorCode != 0) {
            logger.error("Failed to send message with error ${response.errorCode}: ${response.details}")
            exchange.response.statusCode = HttpStatus.BAD_REQUEST
        }

        return response
    }
}
