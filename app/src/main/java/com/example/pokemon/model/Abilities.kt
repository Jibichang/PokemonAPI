package com.example.pokemon.model

import com.squareup.moshi.Json

data class Abilities(
    val ability: Ability,
    @Json(name = "is_hidden") val isHidden: Boolean,
    val slot: Int
)

data class Ability(
    val name: String

)
