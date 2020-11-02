package com.example.drinks.data.repository

import com.example.drinks.domain.model.Drink
import com.example.drinks.domain.model.DrinkEntity
import com.example.drinks.domain.model.Resource

interface Repository {

    suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>>
    suspend fun insertDrinkIntoRoom(drink: DrinkEntity)
    suspend fun getFavouriteDrinks(): Resource<List<DrinkEntity>>
}