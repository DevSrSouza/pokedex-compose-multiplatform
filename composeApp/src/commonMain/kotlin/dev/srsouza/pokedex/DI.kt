package dev.srsouza.pokedex

import dev.srsouza.pokedex.features.pokemonList.pokemonListModule
import org.kodein.di.conf.ConfigurableDI

internal val diGraph = ConfigurableDI().apply {
    addImport(pokemonListModule)
}