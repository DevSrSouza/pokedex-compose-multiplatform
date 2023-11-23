package dev.srsouza.pokedex.features.pokemonDetail.data

import dev.srsouza.pokedex.features.pokemonDetail.domain.Pokemon
import dev.srsouza.pokedex.features.pokemonDetail.domain.PokemonType

interface PokemonDetailRepository {
    suspend fun fetchDetail(pokemonId: String): Pokemon
}

class PokemonDetailRepositoryImpl(
    private val api: PokemonDetailApi,
) : PokemonDetailRepository {
    override suspend fun fetchDetail(pokemonId: String): Pokemon {
        val pokemon = api.detail(pokemonId)

        return Pokemon(
            name = pokemon.name.capitalize(),
            weight = pokemon.weight,
            spriteUrl = pokemon.sprites.spriteUrl,
            types = pokemon.types.map { PokemonType(name = it.type.name.capitalize()) },
        )
    }

}