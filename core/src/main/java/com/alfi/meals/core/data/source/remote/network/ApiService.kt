package com.alfi.meals.core.data.source.remote.network

import com.alfi.meals.core.data.source.remote.response.DetailMealResponse
import com.alfi.meals.core.data.source.remote.response.ListSeafoodResponse
import com.alfi.meals.core.data.source.remote.response.ListVegetarianResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("filter.php")
    suspend fun getAllVegetarian(
        @Query("c") category: String = "Vegetarian"
    ): ListVegetarianResponse

    @GET("filter.php")
    suspend fun getAllSeafood(
        @Query("c") category: String = "Seafood"
    ): ListSeafoodResponse

    @GET("lookup.php")
    suspend fun getDetailMeal(
        @Query("i") id: String?
    ): DetailMealResponse
}