package com.hajhosseinico.max.domain.usecase.home

data class HomeUseCases (
    val getHomePageDefaults: GetHomePageDefaults,
    val getHomePageDefaultsFromRoom: GetHomePageDefaultsFromRoom,
    val getHomeFeatures: GetHomeFeatures,
    val getHomeFeaturesFromRoom: GetHomeFeaturesFromRoom,
    val insertHomeFeaturesIntoDatabase: InsertHomeFeaturesIntoDatabase,
    val insertHomePageDefaultsIntoDatabase: InsertHomeDefaultIntoDatabase,
)