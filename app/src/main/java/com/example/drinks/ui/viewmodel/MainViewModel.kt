package com.example.drinks.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.domain.Repo
import com.example.drinks.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val repo: Repo): ViewModel() {

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

    fun saveDrink(drink: DrinkEntity) {
        viewModelScope.launch {
            repo.insertDrink(drink)
        }
    }

    fun getFavouriteDrinks() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repo.getFavouriteDrinks())

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}