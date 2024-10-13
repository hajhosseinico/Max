package com.hajhosseinico.max.domain.repository

import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.model.HomePagerModel
import kotlinx.coroutines.flow.Flow

interface HomePageRepository {
    fun getHomePageDefaults(): Flow<List<HomePagerModel>>

    fun getHomePageDefaultsFromRoom(): Flow<List<HomePagerModel>>

    fun getHomeFeatures(): Flow<List<HomeFeatureModel>>

    fun getHomeFeaturesFromRoom(): Flow<List<HomeFeatureModel>>
    fun insertHomeFeatureIntoDatabase(homeFeature: HomeFeatureModel): Flow<Boolean>
    fun insertHomePageDefaultIntoDatabase(homePagerModel: HomePagerModel): Flow<Boolean>
}