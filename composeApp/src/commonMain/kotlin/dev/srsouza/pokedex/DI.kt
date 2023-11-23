package dev.srsouza.pokedex

import dev.srsouza.pokedex.features.pokemonDetail.pokemonDetailModule
import dev.srsouza.pokedex.features.pokemonList.pokemonListModule
import dev.srsouza.pokedex.foundation.kodein.kodeinScopesModule
import org.kodein.di.conf.ConfigurableDI

internal val diGraph = ConfigurableDI().apply {
    addImport(kodeinScopesModule)

    addImport(pokemonListModule)
    addImport(pokemonDetailModule)
}