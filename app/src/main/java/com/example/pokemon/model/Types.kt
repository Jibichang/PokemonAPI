package com.example.pokemon.model

import com.squareup.moshi.Json

data class Types(
    val type: Type,
    val slot: Int
)

data class Type(
    val name: String
)