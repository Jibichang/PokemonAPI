package com.example.pokemon.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.api.PokemonApi
import com.example.pokemon.model.PokemonDetail
import com.example.pokemon.model.PokemonProperty
import com.example.pokemon.overview.PokemonApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(pokemonProperty: PokemonProperty, app: Application) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val _selectedProperty = MutableLiveData<PokemonProperty>()
    val selectedProperty: LiveData<PokemonProperty>
        get() = _selectedProperty

    private val _detailProperty = MutableLiveData<PokemonDetail>()
    val detailProperty: LiveData<PokemonDetail>
        get() = _detailProperty

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus>
        get() = _status

    init {
        getPokemonDetail()
        _selectedProperty.value = pokemonProperty
    }

    private fun getPokemonDetail() {
        coroutineScope.launch {
            val resultDeferred = PokemonApi.retrofitService.getPokemon(_selectedProperty.value!!.pokemonId)

            try {
                _status.value = PokemonApiStatus.LOADING
                val result = resultDeferred.await()
                Log.d("getPokemonDetail", " OK $result")
                _detailProperty.value = result
                _status.value = PokemonApiStatus.DONE
            } catch (e: Exception) {
                _status.value = PokemonApiStatus.ERROR
                Log.d("getPokemonDetail", " OK ${e.message}")
                _detailProperty.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}