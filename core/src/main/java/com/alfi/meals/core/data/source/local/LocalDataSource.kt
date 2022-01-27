package com.alfi.meals.core.data.source.local

import com.alfi.meals.core.data.source.local.entity.DetailMealEntity
import com.alfi.meals.core.data.source.local.entity.SeafoodEntity
import com.alfi.meals.core.data.source.local.entity.VegetarianEntity
import com.alfi.meals.core.data.source.local.room.DetailMealDao
import com.alfi.meals.core.data.source.local.room.SeafoodDao
import com.alfi.meals.core.data.source.local.room.VegetarianDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val seafoodDao: SeafoodDao,
    private val vegetarianDao: VegetarianDao,
    private val detailMealDao: DetailMealDao
) {

    fun getAllSeafood(): Flow<List<SeafoodEntity>> = seafoodDao.getSeafoods()

    fun getFavoriteSeafood(): Flow<List<SeafoodEntity>> = seafoodDao.getFavoriteSeafoods()

    suspend fun insertSeafoods(seafoods: List<SeafoodEntity>) = seafoodDao.insertSeafoods(seafoods)

    fun setFavoriteSeafood(seafood: SeafoodEntity, newState: Boolean) {
        seafood.isFavorite = newState
        seafoodDao.setFavoriteSeafood(seafood)
    }

    fun getAllVegetarian(): Flow<List<VegetarianEntity>> = vegetarianDao.getVegetarians()

    fun getFavoriteVegetarian(): Flow<List<VegetarianEntity>> = vegetarianDao.getFavoriteVegetarian()

    suspend fun insertVegetarians(vegetarians: List<VegetarianEntity>) = vegetarianDao.insertVegetarians(vegetarians)

    fun setFavoriteVegetarian(vegetarian: VegetarianEntity, newState: Boolean) {
        vegetarian.isFavorite = newState
        vegetarianDao.setFavoriteVegetarian(vegetarian)
    }

    fun getDetailMeal(): Flow<List<DetailMealEntity>> = detailMealDao.getDetailMeal()

    suspend fun insertDetailMeal(meals: List<DetailMealEntity>) = detailMealDao.insertDetailMeal(meals)

    fun deleteMealFromRoom() = detailMealDao.deleteMeal()
}