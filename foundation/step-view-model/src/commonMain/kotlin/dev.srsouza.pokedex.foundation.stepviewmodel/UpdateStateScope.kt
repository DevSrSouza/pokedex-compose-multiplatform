package dev.srsouza.pokedex.foundation.stepviewmodel

fun interface UpdateStateScope<T> {
    fun update(newState: (current: T) -> T)
}