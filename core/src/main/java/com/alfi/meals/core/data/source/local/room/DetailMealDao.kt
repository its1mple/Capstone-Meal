package com.alfi.meals.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alfi.meals.core.data.source.local.entity.DetailMealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailMealDao {

    @Query("SELECT * FROM DetailMealEntity")
    fun getDetailMeal(): Flow<List<DetailMealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMeal(meals: List<DetailMealEntity>)

    @Query("DELETE FROM DetailMealEntity")
    fun deleteMeal()
}