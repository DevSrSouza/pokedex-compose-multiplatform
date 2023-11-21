package dev.srsouza.pokedex.features.pokemonList.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLJsonQuery(
    val query: String,
    val variables: String? = null,
    val operationName: String,
)