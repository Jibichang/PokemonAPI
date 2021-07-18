package com.example.pokemon.model

import com.squareup.moshi.Json

data class Stats(
    val stat: Stat,
    @Json(name = "base_stat") val baseStat: Int,
    val effort: Int
)

data class Stat(
    val name: String
)
