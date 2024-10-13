package com.hajhosseinico.max.domain.usecase.home

import com.hajhosseinico.max.domain.model.HomePagerModel
import com.hajhosseinico.max.domain.repository.HomePageRepository
import kotlinx.coroutines.flow.Flow

class GetHomePageDefaults (
    private val homePageRepository: HomePageRepository
){
    operator fun invoke(): Flow<List<HomePagerModel>>{
        return homePageRepository.getHomePageDefaults()
    }
}