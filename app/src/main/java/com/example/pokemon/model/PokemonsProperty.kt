package com.example.pokemon.model
import kotlinx.parcelize.Parcelize


data class PokemonsProperty(
 val next: String?,
 val previous: String?,
 val results: List<PokemonProperty>
)