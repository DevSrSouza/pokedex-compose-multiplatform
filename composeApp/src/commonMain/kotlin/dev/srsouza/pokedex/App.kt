package dev.srsouza.pokedex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.srsouza.foundation.theme.AppTheme
import dev.srsouza.pokedex.features.home.presentation.HomeStep
import dev.srsouza.pokedex.navigation.MainNavigator

@Composable
public fun App() = AppTheme {
    Column(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
        MainNavigator(
            diGraph = diGraph,
            initialStep = HomeStep,
        )
    }
}