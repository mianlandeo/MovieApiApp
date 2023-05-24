package com.example.apimovie.di

import com.example.apimovie.BuildConfig
import com.example.apimovie.data.network.ApiClientMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ModuleMovie {

    @Singleton
    @Provides
    fun getProviderRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provides (retrofit: Retrofit): ApiClientMovie {
        return retrofit.create(ApiClientMovie::class.java)
    }
}