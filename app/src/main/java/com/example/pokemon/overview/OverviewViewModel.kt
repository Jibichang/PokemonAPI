package com.example.pokemon.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.api.PokemonApi
import com.example.pokemon.model.PokemonProperty
import com.example.pokemon.model.PokemonsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


enum class PokemonApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {
    private val offset = 0
    private val limit = 20

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<PokemonProperty>()
    val property: LiveData<PokemonProperty>
        get() = _property

    init {
        getPokemonProperties()
    }

    private fun getPokemonProperties() {
        coroutineScope.launch {
            var resultDeferred = PokemonApi.retrofitService.getPokemons(offset, limit)

            try {
                var listResult = resultDeferred.await()
               _response.value = "Success: ${listResult.results.size} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}