package dev.srsouza.pokedex.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import dev.srsouza.pokedex.foundation.components.StepError
import dev.srsouza.pokedex.foundation.stepviewmodel.StepState
import dev.srsouza.pokedex.foundation.stepviewmodel.StepUiState
import dev.srsouza.pokedex.foundation.stepviewmodel.StepViewModel

data class HeaderOptions(
    val hide: Boolean = false,
    val title: String? = null,
    val rightAction: @Composable RowScope.() -> Unit = {},
)

abstract class Step : Screen {
    override val key: ScreenKey = uniqueScreenKey

    open val headerOptions: HeaderOptions
        @Composable get() = remember { HeaderOptions() }
}

abstract class StepStateful<V : StepViewModel<S>, S> : Step() {

    @Composable
    abstract fun initializeViewModel(): V
    
    @Composable
    abstract fun Content(
        viewModel: V,
        state: S,
    )

    @Composable
    final override fun Content() {
        val viewModel = initializeViewModel()
        val state by viewModel.state.collectAsState()

        StepStateHandler(
            state = state
        ) { data ->
            Content(
                viewModel = viewModel,
                state = data,
            )
        }
    }
}

@Composable
fun <S> StepStateHandler(
    state: StepState<S>,
    content: @Composable (S) -> Unit,
) {
    when(state.ui) {
        StepUiState.Content -> content(state.state)
        is StepUiState.Error -> StepError(
            state = (state.ui as StepUiState.Error).state,
            modifier = Modifier.fillMaxSize(),
        )
        StepUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }
    }
}