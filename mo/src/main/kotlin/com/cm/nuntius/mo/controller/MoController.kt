package com.cm.nuntius.mo.controller

import com.cm.nuntius.mo.message.Message
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
class MoController(
    val client: DatabaseClient
) {
    val logger: Logger = LoggerFactory.getLogger(MoController::class.java)

    @PostMapping("/mo")
    @ResponseStatus(HttpStatus.OK)
    fun receive(@RequestBody message: Message) = mono {
        logger.trace("Received MO message with reference ${message.reference}")
        client.sql("INSERT INTO messages (id, timestamp, channel) VALUES ($1, $2, $3)")
            .bind(0, message.reference)
            .bind(1, message.time)
            .bind(2, message.channel)
            .then()
            .awaitSingleOrNull()
    }
}
