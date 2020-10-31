package com.example.drinks.domain

import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.vo.Resource
import javax.inject.Inject

class RepoImpl @Inject constructor(private val dataSource: DataSourceRepo): Repo {

    override suspend fun getDrinksList(drinkName: String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

    override suspend fun getFavouriteDrinks(): Resource<List<DrinkEntity>> {
        return dataSource.getFavouriteDrinks()
    }

    override suspend fun insertDrink(drinkEntity: DrinkEntity) {
        dataSource.insertDrinkIntoRoom(drinkEntity)
    }
}