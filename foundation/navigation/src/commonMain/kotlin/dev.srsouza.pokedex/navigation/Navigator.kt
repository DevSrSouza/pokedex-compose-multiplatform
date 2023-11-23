package dev.srsouza.pokedex.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleOwner
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleStore
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dev.srsouza.foundation.swipeback.VoyagerSwipeBackContent
import dev.srsouza.foundation.swipeback.shouldUseSwipeBack
import dev.srsouza.pokedex.foundation.kodein.rememberDiWithContext
import org.kodein.di.DI
import org.kodein.di.bindings.Scope
import org.kodein.di.bindings.ScopeRegistry
import org.kodein.di.bindings.StandardScopeRegistry
import org.kodein.di.compose.withDI
import org.kodein.di.internal.synchronizedIfNull
import org.kodein.di.on

@OptIn(ExperimentalVoyagerApi::class)
@Composable
fun MainNavigator(
    diGraph: DI,
    initialStep: Step,
) {
    Navigator(
        screen = initialStep,
    ) { navigator ->
        SwipeOrTransition(navigator) { screen ->
            val diWithContext = rememberDiWithContext(diGraph, navigator)
            withDI(diWithContext) {
                NavigationScaffold(navigator, Modifier.fillMaxSize()) {
                    screen.Content()
                }
            }
        }
    }
}

@Composable
private fun SwipeOrTransition(
    navigator: Navigator,
    content: @Composable (Screen) -> Unit
) {
    val supportSwipeBack = remember { shouldUseSwipeBack }

    if(supportSwipeBack) {
        VoyagerSwipeBackContent(navigator, content)
    } else {
        SlideTransition(navigator) { screen ->
            content(screen)
        }
    }
}