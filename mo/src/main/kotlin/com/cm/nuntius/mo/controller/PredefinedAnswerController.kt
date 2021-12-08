package com.cm.nuntius.mo.controller

import com.cm.nuntius.mo.event.PredefinedAnswerCategoryEvent
import com.cm.nuntius.mo.event.PredefinedAnswerEvent
import com.cm.nuntius.mo.json.predefinedanswer.PredefinedAnswer
import com.cm.nuntius.mo.json.predefinedanswer.PredefinedAnswerCategory
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
        logger.info("Updating new predefined answer '${request.name}'")
        answerRepo.save(request)
        event.publish(PredefinedAnswerEvent(request))
    }

    @PostMapping("/delete", consumes = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerDelete(@RequestBody request: PredefinedAnswer) = mono {
        logger.info("Deleting predefined answer '${request.name}' from database")
        answerRepo.delete(request)
        event.publish(PredefinedAnswerEvent(request))
    }

    @GetMapping("/getall", produces = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerGetAll() = mono {
        logger.info("getting all predefined answers")
        return@mono answerRepo.findAll()
    }

    @PostMapping("/category/create", consumes = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerCategoryCreate(@RequestBody request: PredefinedAnswerCategory) = mono {
        logger.info("Saving new predefined answer category '${request.name}' to database")
        categoryRepo.save(request)
        event.publish(PredefinedAnswerCategoryEvent(request))
    }

    @PostMapping("category/delete", consumes = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerCategoryDelete(@RequestBody request: PredefinedAnswerCategory) = mono {
        logger.info("Deleting predefined answer category '${request.name}' from database")
        categoryRepo.delete(request)
        event.publish(PredefinedAnswerCategoryEvent(request))
    }

    @GetMapping("category/getall", produces = [APPLICATION_JSON_VALUE])
    fun predefinedAnswerCategoryGetAll() = mono {
        logger.info("Getting all predefined answers categories")
        return@mono categoryRepo.findAll()
    }
}