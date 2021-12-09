package com.cm.nuntius.mo.controller

import com.cm.nuntius.mo.event.PredefinedAnswerEvent
import com.cm.nuntius.mo.json.predefinedanswer.PredefinedAnswer
import com.cm.nuntius.mo.json.request.PredefinedAnswerCreateRequest
import com.cm.nuntius.mo.repository.predefinedanswer.PredefinedAnswerRepository
import com.cm.nuntius.mo.service.EventService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
class PredefinedAnswerController(
    private val event: EventService,
    private val answerRepo: PredefinedAnswerRepository,
) {
    private val logger: Logger = LoggerFactory.getLogger(MoController::class.java)

    @PostMapping("/predefined", consumes = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerCreate(@RequestBody request: PredefinedAnswerCreateRequest) = mono {
        logger.info("Saving predefined answer '${request.id}' to database")
        val entity = PredefinedAnswer(
            request.id,
            request.text,
            request.category,
            0
        )
        answerRepo.save(entity)
        event.publish(PredefinedAnswerEvent(entity))
    }

    @PatchMapping("/predefined", consumes = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerUpdate(@RequestBody request: PredefinedAnswerCreateRequest) = mono {
        logger.info("Saving predefined answer '${request.id}' to database")
        val entity = PredefinedAnswer(
            request.id,
            request.text,
            request.category,
            0
        )
        answerRepo.delete(entity)
        answerRepo.save(entity)
        event.publish(PredefinedAnswerEvent(entity))
    }

    @DeleteMapping("/predefined", consumes = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerDelete(@RequestBody request: PredefinedAnswerCreateRequest) = mono {
        logger.info("Deleting predefined answer '${request.id}' from database")
        val entity = PredefinedAnswer(
            request.id,
            request.text,
            request.category,
            0
        )
        answerRepo.delete(entity)
        event.publish(PredefinedAnswerEvent(entity))
    }

    @GetMapping("/predefined", produces = [APPLICATION_JSON_VALUE])
    suspend fun predefinedAnswerGetAll(): Collection<PredefinedAnswer> {
        logger.info("getting all predefined answers")
        val list = mutableListOf<PredefinedAnswer>()
        answerRepo.findAll().onEach { list.add(it) }.collect()
        return list
    }
}