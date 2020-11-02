package com.example.drinks.domain.usecases

import com.example.drinks.domain.model.Drink
import com.example.drinks.domain.model.DrinkEntity
import com.example.drinks.domain.model.Resource

interface GetSaveDrinks {

    suspend fun getDrinksList(drinkName: String): Resource<List<Drink>>
    suspend fun getFavouriteDrinks(): Resource<List<DrinkEntity>>
    suspend fun insertDrink(drinkEntity: DrinkEntity)
}