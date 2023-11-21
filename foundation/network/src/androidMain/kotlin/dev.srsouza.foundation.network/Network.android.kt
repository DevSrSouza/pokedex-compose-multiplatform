package dev.srsouza.foundation.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

internal actual val platformHttpEngine: HttpClientEngine = OkHttp.create()