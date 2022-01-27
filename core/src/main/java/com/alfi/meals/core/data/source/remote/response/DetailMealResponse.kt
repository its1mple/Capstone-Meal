package com.alfi.meals.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMealResponse(

	@field:SerializedName("meals")
	val meals: List<MealResponse>? = null
)

data class MealResponse(

	@field:SerializedName("idMeal")
	val id: String? = null,

	@field:SerializedName("strMeal")
	val name: String? = null,

	@field:SerializedName("strCategory")
	val category: String? = null,

	@field:SerializedName("strArea")
	val country: String? = null,

	@field:SerializedName("strMealThumb")
	val image: String? = null,

	@field:SerializedName("strInstructions")
	val recipe: String? = null
)
