package dev.srsouza.pokedex.features.pokemonDetail.domain

data class Pokemon(
    val name: String,
    val weight: Int,
    val spriteUrl: String? = null,
    val types: List<PokemonType>
)