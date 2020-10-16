package com.example.drinks.ui.viewmodel

import androidx.lifecycle.*
import com.example.drinks.domain.Repo
import com.example.drinks.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo): ViewModel() {

    private val drinkData = MutableLiveData<String>()

    fun setDrink(nameDrink: String) {
        drinkData.value = nameDrink
    }

    init {
        setDrink("Margarita")
    }

    val fetchDrinkList = drinkData.distinctUntilChanged().switchMap {

        liveData(Dispatchers.IO) {

            emit(Resource.Loading())
            try {
                emit(repo.getDrinksList(it))

            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }
}