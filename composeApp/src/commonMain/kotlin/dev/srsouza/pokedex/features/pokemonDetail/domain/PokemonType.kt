package dev.srsouza.pokedex.features.pokemonDetail.domain

import kotlinx.serialization.Serializable

@Serializable
data class PokemonType(
    val name: String,
)