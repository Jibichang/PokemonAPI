@file:Suppress("DeferredIsResult")

package com.example.pokemon.api

import com.example.pokemon.model.PokemonDetail
import com.example.pokemon.model.PokemonsProperty
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()



interface PokemonApiService {
    @GET("pokemon")
    fun getPokemons(@Query("offset") offset: Int,
                    @Query("limit") limit: Int): Deferred<PokemonsProperty>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") pokemonId: Int): Deferred<PokemonDetail>

}

object PokemonApi {
    val retrofitService : PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}