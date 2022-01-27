package com.alfi.meals.core.di

import androidx.room.Room
import com.alfi.meals.core.BuildConfig.BASE_URL
import com.alfi.meals.core.BuildConfig.PASSPHRASE
import com.alfi.meals.core.BuildConfig.HOST_NAME
import com.alfi.meals.core.BuildConfig.PINS1
import com.alfi.meals.core.BuildConfig.PINS2
import com.alfi.meals.core.data.MealRepository
import com.alfi.meals.core.data.source.local.LocalDataSource
import com.alfi.meals.core.data.source.local.room.MealDatabase
import com.alfi.meals.core.data.source.remote.RemoteDataSource
import com.alfi.meals.core.data.source.remote.network.ApiService
import com.alfi.meals.core.domain.repository.IMealRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MealDatabase>().seafoodDao() }
    factory { get<MealDatabase>().vegetarianDao() }
    factory { get<MealDatabase>().detailMealDao() }

    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(PASSPHRASE.toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MealDatabase::class.java, "meals.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(HOST_NAME, PINS1)
            .add(HOST_NAME, PINS2)
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get(), get()) }
    single { RemoteDataSource(get()) }
    single<IMealRepository> {
        MealRepository(get(), get())
    }
}