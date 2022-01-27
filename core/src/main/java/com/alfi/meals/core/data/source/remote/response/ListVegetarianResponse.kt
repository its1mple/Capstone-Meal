package com.alfi.meals.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListVegetarianResponse(

	@field:SerializedName("meals")
	val vegetarians: List<VegetarianResponse>? = null
)

data class VegetarianResponse(

	@field:SerializedName("idMeal")
	val id: String? = null,

	@field:SerializedName("strMeal")
	val name: String? = null,

	@field:SerializedName("strMealThumb")
	val image: String? = null
)
