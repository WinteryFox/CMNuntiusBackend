package com.cm.nuntius.mo.controller

import com.cm.nuntius.lib.json.message.MoMessage
import com.cm.nuntius.mo.repository.message.MessageRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class MoController(
    val repository: MessageRepository
) {
    val logger: Logger = LoggerFactory.getLogger(MoController::class.java)

    @PostMapping("/mo")
    @ResponseStatus(HttpStatus.OK)
    fun receive(@RequestBody message: MoMessage) = mono {
        logger.trace("Received MO message with reference ${message.reference}")
        repository.save(message).awaitSingleOrNull()
    }
}
