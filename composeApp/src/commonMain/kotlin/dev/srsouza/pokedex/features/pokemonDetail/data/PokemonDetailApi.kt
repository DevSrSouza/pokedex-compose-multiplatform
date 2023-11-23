package dev.srsouza.pokedex.features.pokemonDetail.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import dev.srsouza.pokedex.features.pokemonDetail.data.model.PokemonDetailResponse

interface PokemonDetailApi {
    @GET("pokemon/{id}")
    suspend fun detail(@Path("id") id: String): PokemonDetailResponse
}