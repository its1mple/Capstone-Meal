package com.alfi.meals

import android.app.Application
import com.alfi.meals.core.di.databaseModule
import com.alfi.meals.core.di.networkModule
import com.alfi.meals.core.di.repositoryModule
import com.alfi.meals.di.useCaseModule
import com.alfi.meals.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}