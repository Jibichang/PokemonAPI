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
    private val limit = 40

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus>
        get() = _status

    private val _property = MutableLiveData<List<PokemonProperty>>()
    val property: LiveData<List<PokemonProperty>>
        get() = _property

    private val _properties = MutableLiveData<List<PokemonProperty>>()
    val properties: LiveData<List<PokemonProperty>>
        get() = _properties

    init {
        getPokemonProperties()
    }

    private fun getPokemonProperties() {
        coroutineScope.launch {
            var resultDeferred = PokemonApi.retrofitService.getPokemons(offset, limit)

            try {
                _status.value = PokemonApiStatus.LOADING
                val listResult = resultDeferred.await().results
                _status.value = PokemonApiStatus.DONE
                if (listResult.isNotEmpty()) {
                    _properties.value = listResult
                }
               _response.value = "Success: ${listResult.size} Mars properties retrieved"
            } catch (e: Exception) {
                _status.value = PokemonApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}