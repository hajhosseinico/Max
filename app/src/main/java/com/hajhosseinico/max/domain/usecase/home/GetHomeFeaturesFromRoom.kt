package com.hajhosseinico.max.domain.usecase.home

import com.hajhosseinico.max.data.local.model.HomeFeatureModelEntity
import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.model.HomePagerModel
import com.hajhosseinico.max.domain.repository.HomePageRepository
import kotlinx.coroutines.flow.Flow

class GetHomeFeaturesFromRoom (
    private val homePageRepository: HomePageRepository
){
    operator fun invoke(): Flow<List<HomeFeatureModel>>{
        return homePageRepository.getHomeFeaturesFromRoom()
    }
}