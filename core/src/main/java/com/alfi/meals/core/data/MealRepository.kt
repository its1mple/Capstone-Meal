package com.alfi.meals.core.data

import com.alfi.meals.core.data.source.local.LocalDataSource
import com.alfi.meals.core.data.source.local.entity.SeafoodEntity
import com.alfi.meals.core.data.source.local.entity.VegetarianEntity
import com.alfi.meals.core.data.source.remote.RemoteDataSource
import com.alfi.meals.core.data.source.remote.network.ApiResponse
import com.alfi.meals.core.data.source.remote.response.MealResponse
import com.alfi.meals.core.data.source.remote.response.SeafoodResponse
import com.alfi.meals.core.data.source.remote.response.VegetarianResponse
import com.alfi.meals.core.domain.model.DetailMeal
import com.alfi.meals.core.domain.model.Seafood
import com.alfi.meals.core.domain.model.Vegetarian
import com.alfi.meals.core.domain.repository.IMealRepository
import com.alfi.meals.core.helper.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MealRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IMealRepository {

    override fun getAllSeafoods(): Flow<Resource<List<Seafood>>> =
        object : NetworkBoundResource<List<Seafood>, List<SeafoodResponse>>() {

            override fun loadFromDB(): Flow<List<Seafood>> =
                localDataSource.getAllSeafood().map { DataMapper.mapSeafoodEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Seafood>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SeafoodResponse>>> =
                remoteDataSource.getAllSeafood()

            override suspend fun saveCallResult(data: List<SeafoodResponse>) {
                val seafoodList = DataMapper.mapSeafoodResponseToEntities(data)
                localDataSource.insertSeafoods(seafoodList)
            }
        }.asFlow()

    override fun getFavoriteSeafoods(): Flow<List<Seafood>> =
        localDataSource.getFavoriteSeafood().map { DataMapper.mapSeafoodEntitiesToDomain(it) }

    override fun setFavoriteSeafood(seafood: Seafood, state: Boolean) {
        val seafoodEntity: SeafoodEntity = DataMapper.mapSeafoodDomainToEntity(seafood)
        CoroutineScope(IO).launch { localDataSource.setFavoriteSeafood(seafoodEntity, state) }
    }

    override fun getAllVegetarians(): Flow<Resource<List<Vegetarian>>> =
        object : NetworkBoundResource<List<Vegetarian>, List<VegetarianResponse>>() {

            override fun loadFromDB(): Flow<List<Vegetarian>> =
                localDataSource.getAllVegetarian().map { DataMapper.mapVegetarianEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Vegetarian>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<VegetarianResponse>>> =
                remoteDataSource.getAllVegetarian()

            override suspend fun saveCallResult(data: List<VegetarianResponse>) {
                val vegetarianList = DataMapper.mapVegetarianResponseToEntities(data)
                localDataSource.insertVegetarians(vegetarianList)
            }

        }.asFlow()

    override fun getFavoriteVegetarians(): Flow<List<Vegetarian>> =
        localDataSource.getFavoriteVegetarian().map { DataMapper.mapVegetarianEntitiesToDomain(it) }

    override fun setFavoriteVegetarian(vegetarian: Vegetarian, state: Boolean) {
        val vegetarianEntity: VegetarianEntity = DataMapper.mapVegetarianDomainToEntity(vegetarian)
        CoroutineScope(IO).launch { localDataSource.setFavoriteVegetarian(vegetarianEntity, state) }
    }

    override fun getDetailMeal(id: String): Flow<Resource<List<DetailMeal>>> =
        object : NetworkBoundResource<List<DetailMeal>, List<MealResponse>>() {

            override fun loadFromDB(): Flow<List<DetailMeal>> =
                localDataSource.getDetailMeal().map { DataMapper.mapMealEntitiesToDomain(it) }

            override fun shouldFetch(data: List<DetailMeal>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MealResponse>>> {
                CoroutineScope(IO).launch { localDataSource.deleteMealFromRoom() }
                return remoteDataSource.getDetailMeal(id)
            }

            override suspend fun saveCallResult(data: List<MealResponse>) {
                val detailMeal = DataMapper.mapMealResponseToEntities(data)
                localDataSource.insertDetailMeal(detailMeal)
            }
        }.asFlow()
}