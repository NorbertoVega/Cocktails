package com.example.drinks.domain

import com.example.drinks.data.DataSource
import com.example.drinks.data.model.Drink
import com.example.drinks.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {

    override suspend fun getDrinksList(drinkName: String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }
}