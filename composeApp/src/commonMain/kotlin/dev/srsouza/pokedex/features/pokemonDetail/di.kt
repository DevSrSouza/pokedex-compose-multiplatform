package dev.srsouza.pokedex.features.pokemonDetail

import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import dev.srsouza.pokedex.features.pokemonDetail.data.PokemonDetailApi
import dev.srsouza.pokedex.features.pokemonDetail.data.PokemonDetailRepository
import dev.srsouza.pokedex.features.pokemonDetail.data.PokemonDetailRepositoryImpl
import dev.srsouza.pokedex.features.pokemonDetail.presentation.PokemonDetailViewModel
import dev.srsouza.pokedex.features.shared.data.PokedexNetwork
import dev.srsouza.pokedex.foundation.kodein.bindScreenScopedSingleton
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.scoped
import org.kodein.di.singleton

@OptIn(ExperimentalVoyagerApi::class)
val pokemonDetailModule = DI.Module("pokemon-detail") {
    bindSingleton<PokemonDetailApi> {
        PokedexNetwork.ktorfit
            .create<PokemonDetailApi>()
    }
    bindSingleton<PokemonDetailRepository> {
        PokemonDetailRepositoryImpl(
            api = instance(),
        )
    }
    bindScreenScopedSingleton {
        PokemonDetailViewModel(
            repository = instance(),
            dispatcher = Dispatchers.Default,
        )
    }
}
