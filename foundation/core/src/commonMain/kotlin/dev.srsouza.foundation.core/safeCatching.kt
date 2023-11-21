package dev.srsouza.foundation.core

import kotlinx.coroutines.CancellationException

public inline fun <R> safeCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        if(e is CancellationException) {
            throw e
        } else {
            Result.failure(e)
        }
    }
}