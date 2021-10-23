package com.cm.nuntius.lib.request

import com.cm.nuntius.lib.request.Request
import com.cm.nuntius.lib.request.RequestBody
import com.cm.nuntius.lib.Route
import kotlinx.serialization.SerializationStrategy

class RequestBuilder<T>(
    private val route: Route<T>
) {
    private var body: RequestBody<*>? = null

    fun <E : Any> body(strategy: SerializationStrategy<E>, body: E) {
        this.body = RequestBody(strategy, body)
    }

    fun build(): Request<*, T> = Request(route, body)
}