package com.alfi.meals.core.helper

import com.alfi.meals.core.data.source.local.entity.DetailMealEntity
import com.alfi.meals.core.data.source.local.entity.SeafoodEntity
import com.alfi.meals.core.data.source.local.entity.VegetarianEntity
import com.alfi.meals.core.data.source.remote.response.MealResponse
import com.alfi.meals.core.data.source.remote.response.SeafoodResponse
import com.alfi.meals.core.data.source.remote.response.VegetarianResponse
import com.alfi.meals.core.domain.model.DetailMeal
import com.alfi.meals.core.domain.model.Seafood
import com.alfi.meals.core.domain.model.Vegetarian

object DataMapper {

    fun mapSeafoodResponseToEntities(input: List<SeafoodResponse>): List<SeafoodEntity> {
        val seafoodList = ArrayList<SeafoodEntity>()
        input.map {
            val seafood = SeafoodEntity(
                null,
                it.id ?: "",
                it.name,
                it.image,
                false
            )
            seafoodList.add(seafood)
        }
        return seafoodList
    }

    fun mapSeafoodEntitiesToDomain(input: List<SeafoodEntity>): List<Seafood> =
        input.map {
            Seafood(
                it.id,
                it.seafoodId,
                it.name,
                it.image,
                it.isFavorite
            )
        }

    fun mapSeafoodDomainToEntity(input: Seafood) =
        SeafoodEntity(
            input.id,
            input.seafoodId ?: "",
            input.name,
            input.image,
            input.isFavorite
        )

    fun mapVegetarianResponseToEntities(input: List<VegetarianResponse>): List<VegetarianEntity> {
        val vegetarianList = ArrayList<VegetarianEntity>()
        input.map {
            val vegetarian = VegetarianEntity(
                null,
                it.id,
                it.name,
                it.image,
                false
            )
            vegetarianList.add(vegetarian)
        }
        return vegetarianList
    }

    fun mapVegetarianEntitiesToDomain(input: List<VegetarianEntity>): List<Vegetarian> =
        input.map {
            Vegetarian(
                it.id,
                it.vegetarianId,
                it.name,
                it.image,
                it.isFavorite
            )
        }

    fun mapVegetarianDomainToEntity(input: Vegetarian) =
        VegetarianEntity(
            input.id,
            input.vegetarianId,
            input.name,
            input.image,
            input.isFavorite
        )

    fun mapMealResponseToEntities(input: List<MealResponse>): List<DetailMealEntity> {
        val mealList = ArrayList<DetailMealEntity>()
        input.map {
            val meal = DetailMealEntity(
                null,
                it.id,
                it.name,
                it.category,
                it.country,
                it.image,
                it.recipe,
                false
            )
            mealList.add(meal)
        }
        return mealList
    }

    fun mapMealEntitiesToDomain(input: List<DetailMealEntity>): List<DetailMeal> =
        input.map {
            DetailMeal(
                it.mealId,
                it.name,
                it.category,
                it.country,
                it.image,
                it.recipe,
                it.isFavorite
            )
        }
}