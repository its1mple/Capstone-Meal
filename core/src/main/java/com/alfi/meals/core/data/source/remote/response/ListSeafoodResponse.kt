package com.alfi.meals.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListSeafoodResponse(

	@field:SerializedName("meals")
	val seafoods: List<SeafoodResponse>? = null
)

data class SeafoodResponse(

	@field:SerializedName("idMeal")
	val id: String? = null,

	@field:SerializedName("strMeal")
	val name: String? = null,

	@field:SerializedName("strMealThumb")
	val image: String? = null
)
