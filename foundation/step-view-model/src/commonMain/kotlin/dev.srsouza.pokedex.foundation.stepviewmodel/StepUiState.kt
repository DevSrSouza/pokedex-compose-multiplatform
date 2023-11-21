package dev.srsouza.pokedex.foundation.stepviewmodel

import dev.srsouza.pokedex.foundation.error.ErrorState

sealed interface StepUiState {
    data object Loading : StepUiState
    data class Error(
        val state: ErrorState
    ) : StepUiState
    data object Content : StepUiState
}