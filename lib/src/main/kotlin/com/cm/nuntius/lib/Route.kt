package com.cm.nuntius.lib

import com.cm.nuntius.lib.response.MtCreateResponse
import io.ktor.http.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json

sealed interface ResponseMapper<T> {
    fun deserialize(json: Json, body: String): T
}

internal class ValueJsonMapper<T>(
    private val strategy: DeserializationStrategy<T>
) : ResponseMapper<T> {
    override fun deserialize(json: Json, body: String): T = json.decodeFromString(strategy, body)
    override fun toString(): String = "ValueJsonMapper(strategy=$strategy)"
}

internal class NullAwareMapper<T>(
    private val strategy: DeserializationStrategy<T>
) : ResponseMapper<T?> {
    override fun deserialize(json: Json, body: String): T? {
        if (body.isBlank()) return null
        return json.decodeFromString(strategy, body)
    }

    override fun toString(): String = "NullAwareMapper(strategy=$strategy)"
}

sealed class Route<T>(
    val method: HttpMethod,
    val path: String,
    val mapper: ResponseMapper<T>
) {
    constructor(
        method: HttpMethod,
        path: String,
        strategy: DeserializationStrategy<T>
    ) : this(method, path, ValueJsonMapper(strategy))

    override fun toString(): String =
        "Route(method:${method.value},path:$path,mapper:$mapper)"

    object MessagePost : Route<MtCreateResponse>(HttpMethod.Post, "/message", MtCreateResponse.serializer())

    companion object {
        const val baseUrl = "https://gw.cmtelecom.com/v1.0"
    }
}
