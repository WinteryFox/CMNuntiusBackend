package com.cm.nuntius.lib

import com.cm.nuntius.lib.request.RequestBuilder
import com.cm.nuntius.lib.request.RequestHandler
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class RestClient(
    @PublishedApi internal val requestHandler: RequestHandler
) {
    @OptIn(ExperimentalContracts::class)
    @PublishedApi
    internal suspend inline fun <T> call(route: Route<T>, builder: RequestBuilder<T>.() -> Unit = {}): T {
        contract {
            callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
        }
        val request = RequestBuilder(route).apply(builder).build()
        return requestHandler.handle(request)
    }
}
