package dev.srsouza.pokedex.features.pokemonList.data

import dev.srsouza.pokedex.features.pokemonList.data.PokemonListRepository.Companion.PAGE_LIMIT
import dev.srsouza.pokedex.features.pokemonList.data.model.GraphQLJsonQuery
import dev.srsouza.pokedex.features.pokemonList.domain.PokemonListItem

interface PokemonListRepository {
    companion object {
        const val PAGE_LIMIT = 50
    }

    suspend fun list(offset: Int = 0): List<PokemonListItem>
}

class PokemonListRepositoryImpl(
    private val api: PokemonListApi,
): PokemonListRepository {
    override suspend fun list(offset: Int): List<PokemonListItem> {
        val opName = "getItems"
        val request = GraphQLJsonQuery(
            query = getQueryItems(opName, offset),
            operationName = opName,
        )

        val response = api.list(request)

        return response.data.spritesAggregate.nodes.mapNotNull { data ->
            PokemonListItem(
                id = data.pokemon.id,
                name = data.pokemon.name,
                spriteUrl = data.sprites.spriteUrl,
            )
        }
    }

    private fun getQueryItems(
        opName: String,
        offset: Int,
    ): String {
        return "query $opName{pokemon_v2_pokemonsprites_aggregate(limit:$PAGE_LIMIT,offset:$offset){nodes{sprites,pokemon_v2_pokemon{id,name}}}}"
    }
}