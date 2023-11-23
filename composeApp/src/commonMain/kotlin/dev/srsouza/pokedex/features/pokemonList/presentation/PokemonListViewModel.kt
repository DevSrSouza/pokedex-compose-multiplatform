package dev.srsouza.pokedex.features.pokemonList.presentation

import dev.srsouza.pokedex.features.pokemonList.data.PokemonListRepository
import dev.srsouza.pokedex.features.pokemonList.domain.PokemonListItem
import dev.srsouza.pokedex.foundation.stepviewmodel.StepViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay

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
    fun loadItems() = state(shouldShowLoading = currentData.items.isEmpty()) {
        if(currentData.shouldLoadMore.not() || currentData.isPageLoading) return@state

        update { it.copy(isPageLoading = true) }

        val newItems = repository.list(currentData.nextOffset)

        if(newItems.isEmpty()) {
            update { currentData ->
                currentData.copy(
                    isPageLoading = false,
                    shouldLoadMore = false,
                )
            }
        } else {
            update { currentData ->
                currentData.copy(
                    items = currentData.items + newItems,
                    isPageLoading = false,
                    nextOffset = currentData.nextOffset + PokemonListRepository.PAGE_LIMIT + 1,
                )
            }
        }
    }
}