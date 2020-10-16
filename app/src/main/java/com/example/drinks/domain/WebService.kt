package com.example.drinks.domain

import com.example.drinks.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET ("search.php")
    suspend fun getDrinkByName(@Query("s") drinkName: String): DrinkList
}