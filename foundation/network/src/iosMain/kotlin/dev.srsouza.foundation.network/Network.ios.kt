package dev.srsouza.foundation.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

internal actual val platformHttpEngine: HttpClientEngine = Darwin.create()