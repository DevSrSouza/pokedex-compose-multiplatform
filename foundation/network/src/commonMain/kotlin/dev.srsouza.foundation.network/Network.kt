package dev.srsouza.foundation.network

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.ktorfit
import dev.srsouza.foundation.serialization.Serialization
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json

internal expect val platformHttpEngine: HttpClientEngine

object Network {
    fun build(baseUrl: String): Ktorfit =
        ktorfit {
            httpClient(defaultHttpEngine(baseUrl))
            converterFactories(UnsuccessResponseConverterFactory())
        }
}

internal fun defaultHttpEngine(
    baseUrl: String,
): HttpClient =
    HttpClient(platformHttpEngine) {
        install(ContentNegotiation) {
            json(Serialization.json)
        }
        install(DefaultRequest) {
            url(baseUrl)
        }
        install(ForceJsonContentTypePlugin)
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
