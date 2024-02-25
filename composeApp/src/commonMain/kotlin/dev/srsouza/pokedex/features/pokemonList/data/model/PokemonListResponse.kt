package dev.srsouza.pokedex.features.pokemonList.data.model

import dev.srsouza.pokedex.features.shared.data.model.PokemonSprites
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpritesGQL(
    val data: PokemonDataSpritesGEL
)

@Serializable
data class PokemonDataSpritesGEL(
    @SerialName("pokemon_v2_pokemonsprites_aggregate") val spritesAggregate: PokemonSpritesAggregateGQL
)

@Serializable
data class PokemonSpritesAggregateGQL(
    val nodes: List<PokemonWithSpriteGQL>,
)

@Serializable
data class PokemonWithSpriteGQL(
    @SerialName("sprites") val sprites: PokemonSprites,
    @SerialName("pokemon_v2_pokemon") val pokemon: PokemonNameResponseGQL,
)

@Serializable
data class PokemonNameResponseGQL(
    val id: String,
    val name: String,
)