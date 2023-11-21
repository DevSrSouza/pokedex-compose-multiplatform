package dev.srsouza.foundation.serialization

import kotlinx.serialization.json.Json

object Serialization {
    val json: Json = Json {
        isLenient = true
        encodeDefaults = true
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
}