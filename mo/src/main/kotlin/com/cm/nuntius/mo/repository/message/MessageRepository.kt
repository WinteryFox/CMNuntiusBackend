package com.cm.nuntius.mo.repository.message

import com.cm.nuntius.lib.message.MoMessage
import reactor.core.publisher.Mono

interface MessageRepository {
    fun save(message: MoMessage): Mono<Void>

    fun delete(id: String): Mono<Void>
}
