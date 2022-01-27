package com.alfi.meals.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMeal(
    val mealId: String? = "",
    val name: String? = "",
    val category: String? = "",
    val country: String? = "",
    val image: String? = "",
    val recipe: String? = "",
    val isFavorite: Boolean = false
) : Parcelable