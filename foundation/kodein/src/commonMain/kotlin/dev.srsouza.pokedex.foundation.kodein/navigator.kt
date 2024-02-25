package dev.srsouza.pokedex.foundation.kodein

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleOwner
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleStore
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.lifecycle.NavigatorDisposable
import cafe.adriel.voyager.navigator.lifecycle.NavigatorLifecycleStore
import org.kodein.di.DI
import org.kodein.di.on

@Composable
fun rememberDiWithContext(di: DI, navigator: Navigator): DI {
    val navigatorScopeContext = rememberNavigatorScopeContext(navigator)
    val screenScopeContext = rememberScreenScopeContext(navigatorScopeContext, navigator)

    return remember(di, screenScopeContext) {
        di.on(screenScopeContext)
    }
}

@OptIn(ExperimentalVoyagerApi::class, InternalVoyagerApi::class)
@Composable
private fun rememberNavigatorScopeContext(
    navigator: Navigator
): NavigatorScopeContext {
    return remember {
        NavigatorScopeContext(
            uniqueId = navigator.key,
            onRegistryScope = { featureScope ->
                NavigatorLifecycleStore.get(navigator) {
                    NavigatorScopeLifecycle { featureScope.dispose() }
                }
            }
        )
    }
}

@Composable
private fun rememberScreenScopeContext(
    navigatorScopeContext: NavigatorScopeContext,
    navigator: Navigator,
): ScreenScopeContext {
    val step = remember { navigator.lastItem }

    return remember {
        ScreenScopeContext(
            navigatorContext = navigatorScopeContext,
            uniqueId = step.key,
            onRegistryScope = { self ->
                ScreenLifecycleStore.get(step) {
                    ScreenScopeLifecycleOwner {
                        self.dispose()
                    }
                }
            }
        )
    }
}

internal class ScreenScopeLifecycleOwner(
    val onDispose: () -> Unit
) : ScreenLifecycleOwner {
    override fun onDispose(screen: Screen) {
        onDispose()
    }
}

@OptIn(ExperimentalVoyagerApi::class)
internal class NavigatorScopeLifecycle(
    val onDispose: () -> Unit,
) : NavigatorDisposable {
    override fun onDispose(navigator: Navigator) {
        onDispose()
    }
}