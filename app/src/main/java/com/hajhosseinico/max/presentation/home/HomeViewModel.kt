package com.hajhosseinico.max.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.model.HomePagerModel
import com.hajhosseinico.max.domain.usecase.home.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val isInternetAvailable: Boolean,
    val homeUseCases: HomeUseCases,
) : ViewModel() {

    fun getFeatures(): Flow<List<HomeFeatureModel>> {
        return if (isInternetAvailable) {
            // Fetch from the web and store each item in the database as it's retrieved
            homeUseCases.getHomeFeatures()
                .map { featureList ->
                    featureList.forEach { homeFeature ->
                        // Launch each insertion asynchronously
                        viewModelScope.launch {
                            homeUseCases.insertHomeFeaturesIntoDatabase(homeFeature).collect()
                        }
                    }
                    featureList // Return the list for the Flow
                }
        } else {
            // Fetch from the local database when offline
            homeUseCases.getHomeFeaturesFromRoom()
        }
    }


    fun getHomePages(): Flow<List<HomePagerModel>> {
        return if (isInternetAvailable) {
            // Fetch from the web and store each item in the database as it's retrieved
            homeUseCases.getHomePageDefaults()
                .map { homePageList ->
                    homePageList.forEach { homePageDefault ->
                        viewModelScope.launch {
                            homeUseCases.insertHomePageDefaultsIntoDatabase(homePageDefault).collect()
                        }
                    }
                    homePageList // Return the list for the Flow
                }
        } else {
            // Fetch from the local database when offline
            homeUseCases.getHomePageDefaultsFromRoom()
        }
    }
}