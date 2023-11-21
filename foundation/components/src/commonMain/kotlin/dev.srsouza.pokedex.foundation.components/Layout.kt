package dev.srsouza.pokedex.foundation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer

@Composable
fun RowScope.Spacer(weight: Float) {
    Spacer(Modifier.weight(weight))
}

@Composable
fun ColumnScope.Spacer(weight: Float) {
    Spacer(Modifier.weight(weight))
}