package com.tiranga.acharyaattendence.di

import com.tiranga.acharyaattendence.api.RetroAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitClient(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("http://74.235.250.30/")
    }

    @Singleton
    @Provides
    fun providesRetroAPI(retrofit: Retrofit.Builder): RetroAPI {
        return retrofit.addConverterFactory(GsonConverterFactory.create()).build().create(RetroAPI::class.java)
    }

}