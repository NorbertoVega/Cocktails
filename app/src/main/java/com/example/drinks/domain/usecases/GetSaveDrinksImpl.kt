package com.example.drinks.domain.usecases

import com.example.drinks.data.repository.Repository
import com.example.drinks.domain.model.Drink
import com.example.drinks.domain.model.DrinkEntity
import com.example.drinks.domain.model.Resource
import javax.inject.Inject

class GetSaveDrinksImpl @Inject constructor(private val repository: Repository): GetSaveDrinks {

    override suspend fun getDrinksList(drinkName: String): Resource<List<Drink>> {
        return repository.getDrinkByName(drinkName)
    }

    override suspend fun getFavouriteDrinks(): Resource<List<DrinkEntity>> {
        return repository.getFavouriteDrinks()
    }

    override suspend fun insertDrink(drinkEntity: DrinkEntity) {
        repository.insertDrinkIntoRoom(drinkEntity)
    }
}