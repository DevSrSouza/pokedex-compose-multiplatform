package dev.srsouza.pokedex.features.pokemonList

import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.kodein.ScreenLifecycleScope
import dev.srsouza.foundation.network.Network
import dev.srsouza.pokedex.features.pokemonList.data.PokedexListApi
import dev.srsouza.pokedex.features.pokemonList.data.PokemonListRepository
import dev.srsouza.pokedex.features.pokemonList.data.PokemonListRepositoryImpl
import dev.srsouza.pokedex.features.pokemonList.presentation.PokemonListViewModel
import dev.srsouza.pokedex.features.shared.data.PokedexNetwork
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.bindings.NoArgBindingDI
import org.kodein.di.instance
import org.kodein.di.scoped
import org.kodein.di.singleton
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalVoyagerApi::class)
val pokemonListModule = DI.Module("pokemon-list") {
    bindSingleton<PokedexListApi> {
        PokedexNetwork.ktorfit
            .create<PokedexListApi>()
    }
    bindSingleton<PokemonListRepository> {
        PokemonListRepositoryImpl(
            api = instance(),
        )
    }
    Bind(binding = scoped(ScreenLifecycleScope.multiItem).singleton {
        PokemonListViewModel(
            repository = instance(),
            dispatcher = Dispatchers.Default,
        )
    })
}
