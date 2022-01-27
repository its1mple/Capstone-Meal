package com.alfi.meals.core.data.source.remote

import com.alfi.meals.core.data.source.remote.network.ApiResponse
import com.alfi.meals.core.data.source.remote.network.ApiService
import com.alfi.meals.core.data.source.remote.response.MealResponse
import com.alfi.meals.core.data.source.remote.response.SeafoodResponse
import com.alfi.meals.core.data.source.remote.response.VegetarianResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllSeafood(): Flow<ApiResponse<List<SeafoodResponse>>> {
        return flow {
            try {
                val response    = apiService.getAllSeafood()
                val seafoodList = response.seafoods
                if (seafoodList?.isNotEmpty() == true) emit(ApiResponse.Success(seafoodList))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(IO)
    }

    suspend fun getAllVegetarian(): Flow<ApiResponse<List<VegetarianResponse>>> {
        return flow {
            try {
                val response       = apiService.getAllVegetarian()
                val vegetarianList = response.vegetarians
                if (vegetarianList?.isNotEmpty() == true) emit(ApiResponse.Success(vegetarianList))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Empty)
            }
        }.flowOn(IO)
    }

    suspend fun getDetailMeal(id: String): Flow<ApiResponse<List<MealResponse>>> {
        return flow {
            try {
                val response = apiService.getDetailMeal(id)
                val detailMeal = response.meals
                if (detailMeal?.isNotEmpty() == true) emit(ApiResponse.Success(detailMeal))
            } catch (e: Exception) {
                emit(ApiResponse.Empty)
            }
        }.flowOn(IO)
    }
}