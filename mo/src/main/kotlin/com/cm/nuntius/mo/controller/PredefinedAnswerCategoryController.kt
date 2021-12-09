package com.cm.nuntius.mo.controller

import com.cm.nuntius.mo.event.PredefinedAnswerCategoryEvent
import com.cm.nuntius.mo.json.predefinedanswer.PredefinedAnswerCategory
import com.cm.nuntius.mo.json.request.PredefinedAnswerCategoryCreateRequest
import com.cm.nuntius.mo.repository.predefinedanswer.PredefinedAnswerCategoryRepository
import com.cm.nuntius.mo.service.EventService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class PredefinedAnswerCategoryController(
    private val event: EventService,
    private val categoryRepo: PredefinedAnswerCategoryRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(MoController::class.java)

    @PostMapping("/predefined/category", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun predefinedAnswerCategoryCreate(@RequestBody request: PredefinedAnswerCategoryCreateRequest) = mono {
        logger.info("Saving new predefined answer category '${request.id}' to database")
        categoryRepo.save(PredefinedAnswerCategory(request.id, 0))
        event.publish(PredefinedAnswerCategoryEvent(PredefinedAnswerCategory(request.id, 0)))
    }

    @DeleteMapping("/predefined/category", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun predefinedAnswerCategoryDelete(@RequestBody request: PredefinedAnswerCategoryCreateRequest) = mono {
        logger.info("Deleting predefined answer category '${request.id}' from database")
        val entity = PredefinedAnswerCategory(
            request.id,
            0
        )
        categoryRepo.delete(entity)
        event.publish(PredefinedAnswerCategoryEvent(entity))
    }

    @GetMapping("/predefined/category", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun predefinedAnswerCategoryGetAll(): Collection<PredefinedAnswerCategory> {
        logger.info("Getting all predefined answers categories")
        val list = mutableListOf<PredefinedAnswerCategory>()
        categoryRepo.findAll().onEach { list.add(it) }.collect()
        return list
    }
}