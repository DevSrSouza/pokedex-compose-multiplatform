package dev.srsouza.pokedex.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenContext
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dev.srsouza.foundation.swipeback.VoyagerSwipeBackContent
import dev.srsouza.foundation.swipeback.shouldUseSwipeBack
import org.kodein.di.DI
import org.kodein.di.compose.withDI
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
            val screenContext = rememberScreenContext()
            withDI(diGraph.on(screenContext)) {
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