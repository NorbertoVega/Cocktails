package com.example.drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinks.AppDatabase
import com.example.drinks.R
import com.example.drinks.data.DataSourceImpl
import com.example.drinks.data.model.Drink
import com.example.drinks.domain.DrinkDao
import com.example.drinks.domain.RepoImpl
import com.example.drinks.ui.viewmodel.MainAdapter
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favourite.*
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : Fragment(), MainAdapter.OnDrinkListener {

    private val viewModel by viewModels<MainViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
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
                    favourite_drinks_recycler.adapter = MainAdapter(requireContext(), list, this)
                }
                is Resource.Failure -> {}
            }
        })
    }

    private fun setupRecyclerView() {
        favourite_drinks_recycler.layoutManager = LinearLayoutManager(requireContext())
        favourite_drinks_recycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onDrinkClicked(item: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink", item)
        findNavController().navigate(R.id.action_favouriteFragment_to_detailFragment, bundle)
    }
}