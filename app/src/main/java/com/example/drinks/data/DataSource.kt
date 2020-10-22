package com.example.drinks.data

import android.util.Log
import com.example.drinks.data.model.Drink
import com.example.drinks.vo.Resource
import com.example.drinks.vo.RetrofitClient
import java.lang.Exception

class DataSource {

    suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>> {
        val result = RetrofitClient.webService.getDrinkByName(drinkName).drinkList
        Log.d("result", "$result")
        return if (result != null && result.isNotEmpty()) Resource.Success(result) else Resource.Failure(NotFoundDrinkException())
    }
}

class NotFoundDrinkException: Exception(){
    override val message: String?
        get() = "The drink entered does not exist"
}