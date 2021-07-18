package com.example.pokemon.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonProperty(
    val name: String,
    val url: String,
): Parcelable {
    val pokemonId: Int
        get() = url.substring(url.lastIndexOf("/", url.length - 2) + 1, url.lastIndexOf("/", url.length)).toInt()
    val imageUrl: String
        get() ="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$pokemonId.png"
}
