package com.hajhosseinico.max.data.repository

import com.hajhosseinico.max.data.local.HomeFeatureDao
import com.hajhosseinico.max.data.local.HomePagerDao
import com.hajhosseinico.max.data.remote.api.ApiService
import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.model.HomePagerModel
import com.hajhosseinico.max.domain.model.toDomainModel
import com.hajhosseinico.max.domain.model.toEntityModel
import com.hajhosseinico.max.domain.repository.HomePageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HomeScreenRepositoryImpl(
    val apiService: ApiService,
    val homeFeatureDao: HomeFeatureDao,
    val homePagerDao: HomePagerDao,
) : HomePageRepository {
    override fun getHomePageDefaults(): Flow<List<HomePagerModel>> = flow {
        val homePageResult = apiService.getHomePageList()
        if (homePageResult.isSuccessful) {
            homePageResult.body()?.let { body ->
                emit(body)
            } ?: emit(emptyList())

        } else {
            throw Exception("Failed to fetch home page defaults: ${homePageResult.message()}")
        }
    }

    // Convert HomePagerModelEntity to HomePagerModel before returning to ViewModel
    override fun getHomePageDefaultsFromRoom(): Flow<List<HomePagerModel>> {
        return homePagerDao.getDashList()
            .map { entityList ->
                entityList.map { it.toDomainModel() } // Conversion happens here
            }
    }

    // Convert HomeFeatureModelEntity to HomeFeatureModel before returning to ViewModel
    override fun getHomeFeaturesFromRoom(): Flow<List<HomeFeatureModel>> {
        return homeFeatureDao.getFeatures()
            .map { entityList ->
                entityList.map { it.toDomainModel() } // Conversion happens here
            }
    }


    override fun getHomeFeatures(): Flow<List<HomeFeatureModel>> = flow {
        val homePageResult = apiService.getFeatures()
        if (homePageResult.isSuccessful) {
            homePageResult.body()?.let { body ->
                emit(body)
            } ?: emit(emptyList())
        } else {
            throw Exception("Failed to fetch home page defaults: ${homePageResult.message()}")
        }
    }

    override fun insertHomeFeatureIntoDatabase(homeFeature: HomeFeatureModel): Flow<Boolean> = flow {
        homeFeatureDao.insert(homeFeature.toEntityModel())
        emit(true)  // Emit success only
    }.catch {
        emit(false)  // Emit false in case of any exception
    }

    override fun insertHomePageDefaultIntoDatabase(homePagerModel: HomePagerModel): Flow<Boolean> = flow {
        homePagerDao.insert(homePagerModel.toEntityModel())
        emit(true)  // Emit success only
    }.catch {
        emit(false)  // Emit false in case of any exception
    }
}