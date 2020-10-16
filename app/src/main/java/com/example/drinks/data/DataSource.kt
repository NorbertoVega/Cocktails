package com.example.drinks.data

import com.example.drinks.data.model.Drink
import com.example.drinks.vo.Resource
import com.example.drinks.vo.RetrofitClient

class DataSource {

    suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webService.getDrinkByName(drinkName).drinkList)
    }
}