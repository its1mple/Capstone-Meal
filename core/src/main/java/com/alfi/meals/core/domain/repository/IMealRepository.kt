package com.alfi.meals.core.domain.repository

import com.alfi.meals.core.data.Resource
import com.alfi.meals.core.domain.model.DetailMeal
import com.alfi.meals.core.domain.model.Seafood
import com.alfi.meals.core.domain.model.Vegetarian
import kotlinx.coroutines.flow.Flow

interface IMealRepository {

    fun getAllSeafoods(): Flow<Resource<List<Seafood>>>

    fun getFavoriteSeafoods(): Flow<List<Seafood>>

    fun setFavoriteSeafood(seafood: Seafood, state: Boolean)

    fun getAllVegetarians(): Flow<Resource<List<Vegetarian>>>

    fun getFavoriteVegetarians(): Flow<List<Vegetarian>>

    fun setFavoriteVegetarian(vegetarian: Vegetarian, state: Boolean)

    fun getDetailMeal(id: String): Flow<Resource<List<DetailMeal>>>
}