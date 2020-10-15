package com.example.drinks.domain

import com.example.drinks.data.model.Drink
import com.example.drinks.vo.Resource

interface Repo {

    fun getDrinksList(): Resource<List<Drink>>
}