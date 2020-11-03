package com.example.drinks.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.drinks.domain.model.DrinkEntity
import com.example.drinks.domain.usecases.GetSaveDrinks
import com.example.drinks.domain.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val getSaveDrinks: GetSaveDrinks, @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val drinkData = MutableLiveData<String>()

    fun setDrink(nameDrink: String) {
        drinkData.value = nameDrink
    }

    init {
        setDrink("Gin")
    }

    val fetchDrinkList = drinkData.distinctUntilChanged().switchMap {

        liveData(Dispatchers.IO) {

            emit(Resource.Loading())
            try {
                emit(getSaveDrinks.getDrinksList(it))

            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun saveDrink(drink: DrinkEntity) {
        viewModelScope.launch {
            getSaveDrinks.insertDrink(drink)
        }
    }

    fun getFavouriteDrinks() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(getSaveDrinks.getFavouriteDrinks())

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}