package dev.srsouza.pokedex.foundation.stepviewmodel

data class StepState<T>(
    val state: T,
    val ui: StepUiState = StepUiState.Content
)