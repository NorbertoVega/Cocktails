package com.example.drinks.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinks.data.model.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_entity")
    suspend fun getAllFavouriteDrinks(): List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(drink: DrinkEntity)
}