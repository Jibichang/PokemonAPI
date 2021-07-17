package com.example.pokemon.model
import android.os.Parcelable

data class PokemonsProperty(
 val next: String?,
 val previous: String?,
 val results: List<PokemonProperty>
)