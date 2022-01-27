package com.alfi.meals.ui.seafood

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alfi.meals.core.data.Resource
import com.alfi.meals.core.domain.model.DetailMeal
import com.alfi.meals.core.domain.model.Seafood
import com.alfi.meals.core.domain.usecase.MealUseCase

class SeafoodViewModel (private val useCase: MealUseCase): ViewModel() {

    val seafoodList = useCase.getAllSeafoods().asLiveData()

    fun getDetailSeafood(id: String) : LiveData<Resource<List<DetailMeal>>> = useCase.getDetailMeal(id).asLiveData()

    fun setFavorite(seafood: Seafood, newStatus: Boolean) = useCase.setFavoriteSeafood(seafood, newStatus)

}