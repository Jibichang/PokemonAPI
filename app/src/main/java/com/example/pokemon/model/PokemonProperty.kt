package com.example.pokemon.model

class PokemonProperty(
    private val name: String,
    private val url: String
) {

    private val pokemonId: Int = url.substring(url.lastIndexOf("/", url.length - 2) + 1, url.lastIndexOf("/", url.length)).toInt()
    private val imageUrl: String ="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$pokemonId.png"
}
