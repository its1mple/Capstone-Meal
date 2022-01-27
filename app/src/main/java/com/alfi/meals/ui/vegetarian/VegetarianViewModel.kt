package com.alfi.meals.ui.vegetarian

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alfi.meals.core.data.Resource
import com.alfi.meals.core.domain.model.DetailMeal
import com.alfi.meals.core.domain.model.Vegetarian
import com.alfi.meals.core.domain.usecase.MealUseCase

class VegetarianViewModel(private val useCase: MealUseCase): ViewModel() {

    val vegetarianList = useCase.getAllVegetarians().asLiveData()

    fun getDetailVegetarian(id: String) : LiveData<Resource<List<DetailMeal>>> = useCase.getDetailMeal(id).asLiveData()

    fun setFavorite(vegetarian: Vegetarian, newStatus: Boolean) { useCase.setFavoriteVegetarian(vegetarian, newStatus) }

}