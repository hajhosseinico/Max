package com.hajhosseinico.max.di

import com.hajhosseinico.max.data.remote.api.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import kotlin.apply
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val networkJson = Json { ignoreUnknownKeys = true }
    @Provides
    fun provideBaseUrl() = "https://6707b71b8e86a8d9e42c9f7d.mockapi.io"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        url:String,
    ): Retrofit  =  Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .client(OkHttpClient.Builder().build())
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(
        ApiService::class.java
    )
}