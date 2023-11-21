package dev.srsouza.pokedex.foundation.error

import androidx.compose.ui.graphics.vector.ImageVector

data class ErrorState(
    val illustration: ImageVector,
    val title: String,
    val button: ErrorStateButton,
)

data class ErrorStateButton(
    val title: String,
    val action: () -> Unit,
)
