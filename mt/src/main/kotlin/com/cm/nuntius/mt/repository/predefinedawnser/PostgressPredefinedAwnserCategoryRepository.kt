package com.cm.nuntius.mt.repository.predefinedawnser

import com.cm.nuntius.mt.json.predefinedawsner.PredefinedAwnser
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class PostgressPredefinedAwnserCategoryRepository(
    val client: DatabaseClient
) : PredefinedAwnserCategoryRepository {
    override fun create(message: PredefinedAwnser): Mono<Void> = mono {
        client.sql("INSERT INTO predefined_answer (id, timestamp, channel) VALUES ($1, $2, $3)")
            .bind(0, message.reference)
            .bind(1, message.timeUtc)
            .bind(2, message.channel)
            .then()
            .awaitSingleOrNull()
    }

    override fun read(id: String): Mono<Void> = mono {
        client.sql("SELECT * FROM predefined_answer WHERE id = $1")
            .bind(0, id)
            .then()
            .awaitSingleOrNull()
    }

    override fun read(id: String): Mono<Void> = mono {
        client.sql()
    }

    override fun delete(id: String): Mono<Void> = mono {
        client.sql("DELETE FROM predefined_answer WHERE id = $1")
            .bind(0, id)
            .then()
            .awaitSingleOrNull()
    }
}