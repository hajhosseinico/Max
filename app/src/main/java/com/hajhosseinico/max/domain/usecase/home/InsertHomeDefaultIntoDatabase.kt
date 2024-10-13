package com.hajhosseinico.max.domain.usecase.home

import com.hajhosseinico.max.domain.model.HomePagerModel
import com.hajhosseinico.max.domain.repository.HomePageRepository
import kotlinx.coroutines.flow.Flow

class InsertHomeDefaultIntoDatabase (
    private val homePageRepository: HomePageRepository
){
    operator fun invoke(homePagerModel: HomePagerModel): Flow<Boolean>{
        return homePageRepository.insertHomePageDefaultIntoDatabase(homePagerModel)
    }
}