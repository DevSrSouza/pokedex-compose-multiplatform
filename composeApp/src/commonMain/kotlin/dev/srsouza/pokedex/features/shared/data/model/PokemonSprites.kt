package dev.srsouza.pokedex.features.shared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSprites(
    @SerialName("front_default") val spriteUrl: String,
)