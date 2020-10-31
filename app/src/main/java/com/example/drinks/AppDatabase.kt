package com.example.drinks

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.domain.DrinkDao

@Database(entities = [DrinkEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao

}