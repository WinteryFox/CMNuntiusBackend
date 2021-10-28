package com.cm.nuntius.mo.service

import com.cm.nuntius.mo.event.Event
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.reactive.asPublisher
import org.springframework.stereotype.Service

@Service
object EventService {
    private val flow = MutableSharedFlow<Event<*>>()
    val publisher get() = flow.asSharedFlow()

    @Synchronized
    suspend fun <T : Event<*>> publish(event: T) = flow.emit(event)
}
