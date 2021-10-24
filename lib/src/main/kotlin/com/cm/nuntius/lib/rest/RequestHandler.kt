package com.cm.nuntius.lib.rest

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import mu.KotlinLogging

class RequestHandler {
    private val logger = KotlinLogging.logger { }
    private val json: Json = Json {
        encodeDefaults = false
        allowStructuredMapKeys = true
        ignoreUnknownKeys = true
        isLenient = true
    }
    private val client: HttpClient = HttpClient(CIO) {
        expectSuccess = false
        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    suspend fun <B : Any, R> handle(request: Request<B, R>): R {
        // TODO: Handle rate limiting
        val httpRequest = client.createRequest(request)
        val response = httpRequest.execute()

        return request.route.mapper.deserialize(json, response.readText())
    }

    private suspend fun <B : Any, R> HttpClient.createRequest(request: Request<B, R>) = request<HttpStatement> {
        method = request.route.method
        url {
            url.takeFrom(Route.baseUrl)
            encodedPath += request.route.path
        }

        run {
            val requestBody = request.body ?: return@run
            body = json.encodeToString(requestBody.strategy, requestBody.body)
            logger.debug { body }
        }
    }
}
