package com.cm.nuntius.mo.controller

import com.cm.nuntius.lib.json.message.MoMessage
import com.cm.nuntius.mo.event.MessageCreateEvent
import com.cm.nuntius.mo.repository.message.MessageRepository
import com.cm.nuntius.mo.service.EventService
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class MoController(
    private val repository: MessageRepository,
    private val event: EventService
) {
    val logger: Logger = LoggerFactory.getLogger(MoController::class.java)

    @GetMapping("/events")
    fun events() = event.publisher

    @PostMapping("/mo")
    @ResponseStatus(HttpStatus.OK)
    fun receive(@RequestBody message: MoMessage) = mono {
        logger.trace("Received MO message with reference ${message.reference}")
        repository.save(message).awaitSingleOrNull()

        event.publish(MessageCreateEvent(message))
    }
}
