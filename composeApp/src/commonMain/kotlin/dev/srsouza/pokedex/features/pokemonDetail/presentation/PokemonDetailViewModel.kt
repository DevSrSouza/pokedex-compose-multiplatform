package dev.srsouza.pokedex.features.pokemonDetail.presentation

import dev.srsouza.pokedex.features.pokemonDetail.data.PokemonDetailRepository
import dev.srsouza.pokedex.features.pokemonDetail.domain.Pokemon
import dev.srsouza.pokedex.foundation.stepviewmodel.StepViewModel
import kotlinx.coroutines.CoroutineDispatcher

data class PokemonDetailState(
    val pokemon: Pokemon? = null,
)

class PokemonDetailViewModel(
    private val repository: PokemonDetailRepository,
    dispatcher: CoroutineDispatcher,
) : StepViewModel<PokemonDetailState>(
    initialContent = PokemonDetailState(),
    dispatcher = dispatcher,
) {
    fun fetchDetail(pokemonId: String) = state {
        val pokemon = repository.fetchDetail(pokemonId)
        update { it.copy(pokemon = pokemon) }
    }
}