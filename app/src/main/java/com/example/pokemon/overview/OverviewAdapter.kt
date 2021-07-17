package com.example.pokemon.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ItemOverviewBinding
import com.example.pokemon.model.PokemonProperty

class OverviewAdapter : ListAdapter<PokemonProperty, OverviewAdapter.PokemonPropertyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonPropertyViewHolder {
        return PokemonPropertyViewHolder(ItemOverviewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PokemonPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }

    class PokemonPropertyViewHolder(private var binding: ItemOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonProperty: PokemonProperty) {
            binding.property = pokemonProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonProperty>() {
        override fun areItemsTheSame(oldItem: PokemonProperty, newItem: PokemonProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonProperty,
            newItem: PokemonProperty
        ): Boolean {
            return oldItem.pokemonId == newItem.pokemonId
        }
    }

}

