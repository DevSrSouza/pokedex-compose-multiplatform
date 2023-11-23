package dev.srsouza.pokedex.foundation.stepviewmodel

import dev.srsouza.foundation.core.safeCatching
import dev.srsouza.pokedex.foundation.error.defaultErrorState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.kodein.di.bindings.ScopeCloseable

abstract class StepViewModel<T>(
    val initialState: StepState<T>,
    val dispatcher: CoroutineDispatcher,
) : ScopeCloseable {

    constructor(
        initialContent: T,
        dispatcher: CoroutineDispatcher,
    ) : this(StepState(initialContent), dispatcher)
    
    val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    
    private val mutableStateFlow: MutableStateFlow<StepState<T>> = MutableStateFlow(initialState)
    val state: StateFlow<StepState<T>> = mutableStateFlow.asStateFlow()
    val currentData: T get() = state.value.state

    public fun state(
        shouldShowLoading: Boolean = true,
        action: suspend UpdateStateScope<T>.() -> Unit,
    ) {
        coroutineScope.launch(dispatcher) {
            if(shouldShowLoading) {
                mutableStateFlow.update { currentState ->
                    currentState.copy(ui = StepUiState.Loading)
                }
            }

            safeCatching {
                val scope = UpdateStateScope<T> { updateCallback ->
                    mutableStateFlow.update { currentState ->
                        currentState.copy(state = updateCallback(currentState.state))
                    }
                }
                action(scope)

                mutableStateFlow.update { currentState ->
                    currentState.copy(ui = StepUiState.Content)
                }
            }.onFailure { error ->
                error.printStackTrace() // TODO: remove or add a Logger module
                mutableStateFlow.update { currentState ->
                    currentState.copy(
                        ui = StepUiState.Error(defaultErrorState(
                            onRetry = { state(action = action) }
                        ))
                    )
                }
            }
        }
    }
    
    open fun onDispose() {}
    
    final override fun close() {
        onDispose()
        coroutineScope.cancel()
    }
}