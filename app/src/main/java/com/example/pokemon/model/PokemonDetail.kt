package com.example.pokemon.model

import com.squareup.moshi.Json
/// https://pokeapi.co/api/v2/pokemon/{name or id}
data class PokemonDetail(
    val abilities: List<Abilities>,
    @Json(name = "base_experience") val baseExperience: Int,
    val forms: List<Forms>,
    val height: Int,
    @Json(name = "id") val pokemonId: Int,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val types: List<Types>,
    val weight: Int,
    val stats: List<Stats>
)
