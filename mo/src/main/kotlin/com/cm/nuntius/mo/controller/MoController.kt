package com.cm.nuntius.mo.controller

import com.cm.nuntius.lib.json.message.MoMessage
import com.cm.nuntius.lib.json.response.StatusResponse
import com.cm.nuntius.mo.StatusReport
import com.cm.nuntius.mo.event.MessageCreateEvent
import com.cm.nuntius.mo.event.StatusEvent
import com.cm.nuntius.mo.repository.message.MessageRepository
import com.cm.nuntius.mo.service.EventService
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
class MoController(
    private val repository: MessageRepository,
    private val event: EventService
) {
    private val logger: Logger = LoggerFactory.getLogger(MoController::class.java)

    @GetMapping("/events")
    fun events() = event.publisher

    @PostMapping("/mo")
    @ResponseStatus(HttpStatus.OK)
    fun receive(@RequestBody message: MoMessage) = mono {
        logger.info("Received MO message with reference ${message.reference}")
        repository.save(message).awaitSingleOrNull()
        event.publish(MessageCreateEvent(message))
    }

    @PostMapping("/status", consumes = [APPLICATION_JSON_VALUE])
    suspend fun status(@RequestBody response: StatusResponse) {
        if (response.messages.msg.status.errorCode.isNotBlank()) {
            logger.error("Failed to deliver message ${response.messages.msg.reference} with error ${response.messages.msg.status.errorCode} (${response.messages.msg.status.errorDescription})")
            return
        }

        val status = when (response.messages.msg.status.code) {
            "0" -> "ACCEPTED"
            "1" -> "REJECTED"
            "2" -> "DELIVERED"
            "3" -> "FAILED"
            "4" -> "READ"
            else -> "UNKNOWN"
        }
        logger.info("Received status rapport with reference ${response.messages.msg.reference} status $status")
        event.publish(
            StatusEvent(
                StatusReport(
                    response.messages.msg.status.code.toInt(),
                    status,
                    response.messages.msg.reference,
                    response.messages.msg.to
                )
            )
        )
    }
}
