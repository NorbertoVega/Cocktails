package com.example.drinks.data

import android.util.Log
import com.example.drinks.AppDatabase
import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.domain.DataSourceRepo
import com.example.drinks.domain.DrinkDao
import com.example.drinks.vo.Resource
import com.example.drinks.vo.RetrofitClient
import java.lang.Exception
import javax.inject.Inject

class DataSourceImpl@Inject constructor(private val drinkDao: DrinkDao): DataSourceRepo {

    override suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>> {
        val result = RetrofitClient.webService.getDrinkByName(drinkName).drinkList
        Log.d("result", "$result")
        return if (result != null && result.isNotEmpty()) Resource.Success(result) else Resource.Failure(NotFoundDrinkException())
    }

    override suspend fun insertDrinkIntoRoom(drink: DrinkEntity) {
        drinkDao.insertFavourite(drink)
    }

    override suspend fun getFavouriteDrinks(): Resource<List<DrinkEntity>>{
        return Resource.Success(drinkDao.getAllFavouriteDrinks())
    }
}

class NotFoundDrinkException: Exception(){
    override val message: String?
        get() = "The drink entered does not exist"
}