package dev.srsouza.pokedex.features.pokemonDetail.data.model

import dev.srsouza.pokedex.features.pokemonDetail.domain.PokemonType
import dev.srsouza.pokedex.features.shared.data.model.PokemonSprites
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val name: String,
    val sprites: PokemonSprites,
    val types: List<PokemonTypeBody>,
    val weight: Int,
)

@Serializable
data class PokemonTypeBody(
    val type: PokemonType,
)

