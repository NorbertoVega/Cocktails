package com.example.drinks.data.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinks.domain.model.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_entity")
    suspend fun getAllFavouriteDrinks(): List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(drink: DrinkEntity)
}