package com.hajhosseinico.max.data.remote.api

import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.model.HomePagerModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/Dashboard")
    suspend fun getHomePageList() : Response<List<HomePagerModel>>

    @GET("api/v1/features")
    suspend fun getFeatures() : Response<List<HomeFeatureModel>>
}