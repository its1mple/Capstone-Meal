package com.alfi.meals.core.domain.usecase

import com.alfi.meals.core.data.Resource
import com.alfi.meals.core.domain.model.DetailMeal
import com.alfi.meals.core.domain.model.Seafood
import com.alfi.meals.core.domain.model.Vegetarian
import com.alfi.meals.core.domain.repository.IMealRepository
import kotlinx.coroutines.flow.Flow

class MealInteractor(private val repository: IMealRepository): MealUseCase {

    override fun getAllSeafoods(): Flow<Resource<List<Seafood>>> = repository.getAllSeafoods()

    override fun getFavoriteSeafoods(): Flow<List<Seafood>> = repository.getFavoriteSeafoods()

    override fun setFavoriteSeafood(seafood: Seafood, state: Boolean) = repository.setFavoriteSeafood(seafood, state)

    override fun getAllVegetarians(): Flow<Resource<List<Vegetarian>>> = repository.getAllVegetarians()

    override fun getFavoriteVegetarians(): Flow<List<Vegetarian>> = repository.getFavoriteVegetarians()

    override fun setFavoriteVegetarian(vegetarian: Vegetarian, state: Boolean) = repository.setFavoriteVegetarian(vegetarian, state)

    override fun getDetailMeal(id: String): Flow<Resource<List<DetailMeal>>> = repository.getDetailMeal(id)

}