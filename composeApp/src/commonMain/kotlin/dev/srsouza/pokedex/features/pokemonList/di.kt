package dev.srsouza.pokedex.features.pokemonList

import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import dev.srsouza.pokedex.features.pokemonList.data.PokemonListApi
import dev.srsouza.pokedex.features.pokemonList.data.PokemonListRepository
import dev.srsouza.pokedex.features.pokemonList.data.PokemonListRepositoryImpl
import dev.srsouza.pokedex.features.pokemonList.presentation.PokemonListViewModel
import dev.srsouza.pokedex.features.shared.data.PokedexNetwork
import dev.srsouza.pokedex.foundation.kodein.bindScreenScopedSingleton
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.scoped
import org.kodein.di.singleton

@OptIn(ExperimentalVoyagerApi::class)
val pokemonListModule = DI.Module("pokemon-list") {
    bindSingleton<PokemonListApi> {
        PokedexNetwork.ktorfit
            .create<PokemonListApi>()
    }
    bindSingleton<PokemonListRepository> {
        PokemonListRepositoryImpl(
            api = instance(),
        )
    }
    bindScreenScopedSingleton {
        PokemonListViewModel(
            repository = instance(),
            dispatcher = Dispatchers.Default,
        )
    }
}
