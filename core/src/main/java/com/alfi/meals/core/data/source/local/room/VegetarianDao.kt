package com.alfi.meals.core.data.source.local.room

import androidx.room.*
import com.alfi.meals.core.data.source.local.entity.VegetarianEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VegetarianDao {

    @Query("SELECT * FROM VegetarianEntity")
    fun getVegetarians(): Flow<List<VegetarianEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVegetarians(vegetarians: List<VegetarianEntity>)

    @Query("SELECT * FROM VegetarianEntity WHERE isFavorite = 1")
    fun getFavoriteVegetarian(): Flow<List<VegetarianEntity>>

    @Update
    fun setFavoriteVegetarian(vegetarian: VegetarianEntity)
}