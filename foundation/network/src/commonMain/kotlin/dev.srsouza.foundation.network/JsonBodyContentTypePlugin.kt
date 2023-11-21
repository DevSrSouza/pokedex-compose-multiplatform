package dev.srsouza.foundation.network

import dev.srsouza.foundation.core.safeCatching
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.serializer

internal val ForceJsonContentTypePlugin = createClientPlugin("ForceJsonContentTypePlugin") {
    onRequest { request, content ->
        val type = request.bodyType?.kotlinType

        if (type != null && request.contentType() == null) {
            safeCatching { serializer(type) }
                .onSuccess {
                    request.contentType(ContentType.Application.Json)
                }
        }
    }
}