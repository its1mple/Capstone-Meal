package com.alfi.meals.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alfi.meals.core.domain.usecase.MealUseCase

class FavoriteViewModel(useCase: MealUseCase): ViewModel() {

    val favoriteSeafood = useCase.getFavoriteSeafoods().asLiveData()

    val favoriteVegetarian = useCase.getFavoriteVegetarians().asLiveData()
}