package com.alfi.meals.core.data.source.local.room

import androidx.room.*
import com.alfi.meals.core.data.source.local.entity.SeafoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeafoodDao {

    @Query("SELECT * FROM SeafoodEntity")
    fun getSeafoods(): Flow<List<SeafoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeafoods(seafoods: List<SeafoodEntity>)

    @Query("SELECT * FROM SeafoodEntity WHERE isFavorite = 1")
    fun getFavoriteSeafoods(): Flow<List<SeafoodEntity>>

    @Update
    fun setFavoriteSeafood(seafood: SeafoodEntity)
}