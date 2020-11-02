package com.example.drinks.data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.drinks.domain.model.DrinkEntity

@Database(entities = [DrinkEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao

}