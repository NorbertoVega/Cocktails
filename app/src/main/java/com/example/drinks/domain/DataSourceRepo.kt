package com.example.drinks.domain

import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.vo.Resource

interface DataSourceRepo {

    suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>>
    suspend fun insertDrinkIntoRoom(drink: DrinkEntity)
    suspend fun getFavouriteDrinks(): Resource<List<DrinkEntity>>
}