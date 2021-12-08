package com.cm.nuntius.mo.controller

import com.cm.nuntius.mo.event.PredefinedAnswerEvent
import com.cm.nuntius.mo.json.predefinedanswer.PredefinedAnswer
import com.cm.nuntius.mo.repository.predefinedanswer.PredefinedAnswerCategoryRepository
import com.cm.nuntius.mo.repository.predefinedanswer.PredefinedAnswerRepository
import com.cm.nuntius.mo.service.EventService
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/predefined")
class PredefinedAnswerController(
    private val event: EventService,
    private val answerRepo: PredefinedAnswerRepository,
    private val categoryRepo: PredefinedAnswerCategoryRepository
){
    private val logger: Logger = LoggerFactory.getLogger(MoController::class.java)

    @GetMapping("/events")
    fun events() = event.publisher

    @PostMapping("/create", consumes = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerCreate(@RequestBody request: PredefinedAnswer) = mono {
        logger.info("Saving new predefined answer '${request.name}' to database")
        answerRepo.save(request)
        event.publish(PredefinedAnswerEvent(request))
    }

    @PostMapping("/update", consumes = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerUpdate(@RequestBody request: PredefinedAnswer) = mono {
        logger.info("Saving new predefined answer '${request.name}' to database")
        answerRepo.save(request)
        event.publish(PredefinedAnswerEvent(request))
    }
}