package com.example.pokemon.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ItemOverviewBinding
import com.example.pokemon.model.PokemonProperty

class OverviewAdapter(val onClickListener: OnClickListener) : ListAdapter<PokemonProperty, OverviewAdapter.PokemonPropertyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonPropertyViewHolder {
        return PokemonPropertyViewHolder(ItemOverviewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PokemonPropertyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
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

    class OnClickListener(val clickListener: (pokemonProperty: PokemonProperty) -> Unit) {
        fun onClick(pokemonProperty:PokemonProperty) = clickListener(pokemonProperty)
    }

}

