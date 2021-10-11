package com.cm.nuntius.mo.repository.message

import com.cm.nuntius.mo.message.Message
import reactor.core.publisher.Mono

interface MessageRepository {
    fun save(message: Message): Mono<Void>

    fun delete(id: String): Mono<Void>
}
