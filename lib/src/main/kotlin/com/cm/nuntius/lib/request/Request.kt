package com.cm.nuntius.lib.request

import com.cm.nuntius.lib.Route
import kotlinx.serialization.SerializationStrategy

data class RequestBody<T>(
    val strategy: SerializationStrategy<T>,
    val body: T
) where T : Any

class Request<B : Any, R>(
    val route: Route<R>,
    val body: RequestBody<B>?,
)
