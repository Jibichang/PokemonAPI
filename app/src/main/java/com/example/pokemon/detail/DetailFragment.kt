package com.example.pokemon.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentDetailBinding
import com.example.pokemon.overview.OverviewFragmentDirections
import com.example.pokemon.overview.OverviewViewModel

class DetailFragment : Fragment() {
//    private val viewModel: DetailViewModel by lazy {
//        ViewModelProvider(this).get(DetailViewModel::class.java)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val pokemonProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = DetailViewModelFactory(pokemonProperty, application)
//        binding.viewModel = ViewModelProvider(
//            this, viewModelFactory).get(DetailViewModel::class.java)
        val viewModel: DetailViewModel by lazy {
            ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        }


        binding.viewModel = viewModel
        return binding.root
    }
}