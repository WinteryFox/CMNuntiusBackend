package com.cm.nuntius.mt.repository.predefinedawnser

import com.cm.nuntius.mt.json.predefinedawsner.PredefinedAwnser
import reactor.core.publisher.Mono

interface PredefinedAwnserCategoryRepository {
    fun create(awnser: PredefinedAwnser): Mono<Void>

    fun read(id: String): Mono<Void>

    fun update(id: String): Mono<Void>

    fun delete(id: String): Mono<Void>
}