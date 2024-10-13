package com.hajhosseinico.max.domain.usecase.home

import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.repository.HomePageRepository
import kotlinx.coroutines.flow.Flow

class InsertHomeFeaturesIntoDatabase (
    private val homePageRepository: HomePageRepository
){
    operator fun invoke(homeFeature: HomeFeatureModel): Flow<Boolean>{
        return homePageRepository.insertHomeFeatureIntoDatabase(homeFeature)
    }
}