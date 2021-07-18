package com.example.pokemon.model

import com.squareup.moshi.Json


data class Sprites(
    val other: Other
)

data class Other(
    @Json(name = "official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @Json(name = "front_default") val frontDefault: String

)