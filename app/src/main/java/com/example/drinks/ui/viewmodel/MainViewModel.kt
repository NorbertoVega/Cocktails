package com.example.drinks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.drinks.domain.Repo
import com.example.drinks.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo): ViewModel() {

    val fetchDrinkList = liveData(Dispatchers.IO) {

        emit(Resource.Loading())

        try {
            emit(repo.getDrinksList())

        } catch (e: Exception) {

            emit(Resource.Failure(e))
        }
    }
}