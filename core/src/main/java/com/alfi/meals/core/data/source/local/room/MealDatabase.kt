package com.alfi.meals.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alfi.meals.core.data.source.local.entity.DetailMealEntity
import com.alfi.meals.core.data.source.local.entity.SeafoodEntity
import com.alfi.meals.core.data.source.local.entity.VegetarianEntity

@Database(entities = [SeafoodEntity::class, VegetarianEntity::class, DetailMealEntity::class], version = 1, exportSchema = false)
abstract class MealDatabase: RoomDatabase() {

    abstract fun seafoodDao(): SeafoodDao
    abstract fun vegetarianDao(): VegetarianDao
    abstract fun detailMealDao(): DetailMealDao

}
