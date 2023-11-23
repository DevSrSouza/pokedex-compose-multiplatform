package dev.srsouza.pokedex.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import dev.srsouza.pokedex.foundation.components.HeaderBar
import dev.srsouza.pokedex.foundation.components.NavigationIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

public val LocalNavigationScaffold: ProvidableCompositionLocal<NavigationScaffold> =
    staticCompositionLocalOf { error("NavigationScaffold not initialized") }

@OptIn(ExperimentalMaterial3Api::class)
class NavigationScaffold(
    private val coroutineScope: CoroutineScope,
    private val sheetState: SheetState,
) {
    internal var currentBottomSheetContent: @Composable () -> Unit by mutableStateOf({})

    fun showBottomSheet(content: @Composable () -> Unit) {
        currentBottomSheetContent = content
        coroutineScope.launch {
            sheetState.show()
        }
    }

    fun hideBottomSheet() {
        coroutineScope.launch {
            sheetState.hide()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NavigationScaffold(
    navigator: Navigator,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            val icon = remember(navigator.size) {
                if(navigator.size > 1) NavigationIcon.BACK else NavigationIcon.CLOSE
            }
            val currentStep = navigator.lastItem as Step
            val headerOptions = currentStep.headerOptions
            val shouldShow = remember(headerOptions.hide) { headerOptions.hide.not() }

            if(shouldShow) {
                HeaderBar(
                    navigationIcon = icon,
                    navigationAction = { navigator.pop() },
                    title = headerOptions.title,
                    rightActions = headerOptions.rightAction,
                )
            }
        }
    ) { padding ->
        val sheetState = rememberModalBottomSheetState()
        val coroutineScope = rememberCoroutineScope()
        val navigationScaffold = remember(coroutineScope) {
            NavigationScaffold(
                coroutineScope = coroutineScope,
                sheetState = sheetState
            )
        }

        CompositionLocalProvider(
            LocalNavigationScaffold provides navigationScaffold
        ) {
            Column(modifier = Modifier.padding(padding)) {
                content()

                if(sheetState.isVisible) {
                    ModalBottomSheet(
                        onDismissRequest = {},
                        sheetState = sheetState,
                    ) {
                        LocalNavigationScaffold.current.currentBottomSheetContent()
                    }
                }
            }
        }
    }
}