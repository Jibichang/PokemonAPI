package com.example.pokemon.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.model.PokemonProperty

class DetailViewModelFactory(
    private val marsProperty: PokemonProperty,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(marsProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}