package com.example.pokemon.model

class PokemonProperty(
    val name: String,
    val url: String
) {

    val pokemonId: Int = url.substring(url.lastIndexOf("/", url.length - 2) + 1, url.lastIndexOf("/", url.length)).toInt()
    val imageUrl: String ="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$pokemonId.png"
}
