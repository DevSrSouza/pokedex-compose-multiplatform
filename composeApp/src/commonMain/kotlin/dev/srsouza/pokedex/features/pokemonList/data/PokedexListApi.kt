package dev.srsouza.pokedex.features.pokemonList.data

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import dev.srsouza.pokedex.features.pokemonList.data.model.GraphQLJsonQuery
import dev.srsouza.pokedex.features.pokemonList.data.model.PokemonSpritesGQL

interface PokedexListApi {
    @POST("https://beta.pokeapi.co/graphql/v1beta")
    suspend fun list(@Body request: GraphQLJsonQuery): PokemonSpritesGQL
}