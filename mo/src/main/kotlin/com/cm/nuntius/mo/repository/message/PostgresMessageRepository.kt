package com.cm.nuntius.mo.repository.message

import com.cm.nuntius.lib.json.message.MoMessage
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class PostgresMessageRepository(
    val client: DatabaseClient
) : MessageRepository {
    override fun save(message: MoMessage): Mono<Void> = mono {
        client.sql("INSERT INTO messages (id, timestamp, channel) VALUES ($1, $2, $3)")
            .bind(0, message.reference)
            .bind(1, message.timeUtc)
            .bind(2, message.channel)
            .then()
            .awaitSingleOrNull()
    }

    override fun delete(id: String): Mono<Void> = mono {
        client.sql("DELETE FROM messages WHERE id = $1")
            .bind(0, id)
            .then()
            .awaitSingleOrNull()
    }
}