package dev.srsouza.pokedex.features.pokemonList.presentation

import dev.srsouza.pokedex.features.pokemonList.data.PokemonListRepository
import dev.srsouza.pokedex.features.pokemonList.domain.PokemonListItem
import dev.srsouza.pokedex.foundation.stepviewmodel.StepViewModel
import kotlinx.coroutines.CoroutineDispatcher

data class PokemonListState(
    val isPageLoading: Boolean = false,
    val items: List<PokemonListItem> = emptyList(),
    val nextOffset: Int = 0,
    val shouldLoadMore: Boolean = true,
)

class PokemonListViewModel(
    private val repository: PokemonListRepository,
    dispatcher: CoroutineDispatcher,
) : StepViewModel<PokemonListState>(
    initialContent = PokemonListState(),
    dispatcher = dispatcher,
) {
    fun loadItems() = state {
        val currentState = state.value.state

        update { it.copy(isPageLoading = true) }

        val newItems = repository.list(currentState.nextOffset)
        update {
            currentState.copy(
                items = newItems,
                isPageLoading = false,
            )
        }
    }
}