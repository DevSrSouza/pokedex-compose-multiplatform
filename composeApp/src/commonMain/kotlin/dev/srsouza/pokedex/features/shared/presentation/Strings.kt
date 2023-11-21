package dev.srsouza.pokedex.features.shared.presentation

import cafe.adriel.lyricist.LyricistStrings

@LyricistStrings(languageTag = "EN", default = true)
val EnStrings = PokedexStrings(
    home = PokedexHomeStrings(),
    pokemonList = PokedexPokemonListStrings(),
)

data class PokedexStrings(
    val home: PokedexHomeStrings,
    val pokemonList: PokedexPokemonListStrings,
)

data class PokedexHomeStrings(
    val title: String = "Pokedex",
    val pokemonListCardTitle: String = "Pokemon List",
)

data class PokedexPokemonListStrings(
    val headerTitle: String = "Pokemon List",
)