package com.example.drinks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinks.R
import com.example.drinks.databinding.FragmentFavouriteBinding
import com.example.drinks.domain.model.Drink
import com.example.drinks.ui.adapter.MainAdapter
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.domain.model.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(), MainAdapter.OnDrinkListener {

    private val viewModel by viewModels<MainViewModel>()
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getFavouriteDrinks().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val list = it.data.map {
                        Drink(it.drinkId, it.image, it.name, it.description, it.hasAlcohol)
                    }
                    binding.favouriteDrinksRecycler.adapter = MainAdapter(requireContext(), list, this)
                }
                is Resource.Failure -> {}
            }
        })
    }

    private fun setupRecyclerView() {
        binding.favouriteDrinksRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.favouriteDrinksRecycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onDrinkClicked(item: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink", item)
        findNavController().navigate(R.id.action_favouriteFragment_to_detailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}