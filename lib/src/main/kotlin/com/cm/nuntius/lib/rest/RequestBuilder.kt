package com.cm.nuntius.lib.rest

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