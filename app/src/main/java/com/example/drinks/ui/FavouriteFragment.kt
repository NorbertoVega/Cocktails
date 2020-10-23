package com.example.drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.drinks.AppDatabase
import com.example.drinks.R
import com.example.drinks.data.DataSource
import com.example.drinks.domain.RepoImpl
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.ui.viewmodel.VMFactory
import com.example.drinks.vo.Resource

class FavouriteFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext)))) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavouriteDrinks().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Log.d("Favourite", "${it.data}")
                }
                is Resource.Failure -> {}
            }
        })
    }
}