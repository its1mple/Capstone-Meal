package com.alfi.meals.di

import com.alfi.meals.core.domain.usecase.MealInteractor
import com.alfi.meals.core.domain.usecase.MealUseCase
import com.alfi.meals.ui.seafood.SeafoodViewModel
import com.alfi.meals.ui.vegetarian.VegetarianViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MealUseCase> { MealInteractor(get()) }
}

val viewModelModule = module {
    viewModel { SeafoodViewModel(get()) }
    viewModel { VegetarianViewModel(get()) }
}