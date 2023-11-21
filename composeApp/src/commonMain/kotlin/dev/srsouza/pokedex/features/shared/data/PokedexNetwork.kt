package dev.srsouza.pokedex.features.shared.data

import dev.srsouza.foundation.network.Network

object PokedexNetwork {
    private const val POKEMON_API_BASE_URL = "https://pokeapi.co/api/v2/"

    val ktorfit by lazy {
        Network.build(POKEMON_API_BASE_URL)
    }
}