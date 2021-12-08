package com.cm.nuntius.mt.repository.predefinedawnser

import com.cm.nuntius.mt.json.predefinedanswer.PredefinedAnswer
import reactor.core.publisher.Mono

// Extend CoroutineCrudRepository en maak het een interface
interface PredefinedAnswerCategoryRepository {
    fun create(answer: PredefinedAnswer): Mono<Void>

    fun read(id: String): Mono<Void>

    fun update(id: String): Mono<Void>

    fun delete(id: String): Mono<Void>
}