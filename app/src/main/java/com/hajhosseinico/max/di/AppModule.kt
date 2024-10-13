package com.hajhosseinico.max.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.util.appendPlaceholders
import com.hajhosseinico.max.data.local.HomeFeatureDao
import com.hajhosseinico.max.data.local.HomePagerDao
import com.hajhosseinico.max.data.local.MaxDatabase
import com.hajhosseinico.max.data.remote.api.ApiService
import com.hajhosseinico.max.data.repository.HomeScreenRepositoryImpl
import com.hajhosseinico.max.domain.repository.HomePageRepository
import com.hajhosseinico.max.domain.usecase.home.GetHomeFeatures
import com.hajhosseinico.max.domain.usecase.home.GetHomeFeaturesFromRoom
import com.hajhosseinico.max.domain.usecase.home.GetHomePageDefaults
import com.hajhosseinico.max.domain.usecase.home.GetHomePageDefaultsFromRoom
import com.hajhosseinico.max.domain.usecase.home.HomeUseCases
import com.hajhosseinico.max.domain.usecase.home.InsertHomeDefaultIntoDatabase
import com.hajhosseinico.max.domain.usecase.home.InsertHomeFeaturesIntoDatabase
import com.hajhosseinico.max.util.isInternetAvailable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @ApplicationContext
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    fun provideHomePageRepository(
        apiService: ApiService,
        homeFeatureDao: HomeFeatureDao,
        homePagerDao: HomePagerDao,
    ): HomePageRepository {
        return HomeScreenRepositoryImpl(apiService, homeFeatureDao, homePagerDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): MaxDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = MaxDatabase::class.java,
            name = "max_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDashDao(
        maxDatabase: MaxDatabase
    ): HomePagerDao = maxDatabase.homePagerDao

    @Provides
    @Singleton
    fun provideFeatureDao(
        maxDatabase: MaxDatabase
    ): HomeFeatureDao = maxDatabase.homeFeatureDao

    @Provides
    @Singleton
    fun provideHelper(
        application: Application
    ): Boolean {
        return isInternetAvailable(application)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        homePageRepository: HomePageRepository
    ): HomeUseCases {
        return HomeUseCases(
            getHomePageDefaults = GetHomePageDefaults(homePageRepository),
            getHomePageDefaultsFromRoom = GetHomePageDefaultsFromRoom(homePageRepository),
            getHomeFeatures = GetHomeFeatures(homePageRepository),
            getHomeFeaturesFromRoom = GetHomeFeaturesFromRoom(homePageRepository),
            insertHomePageDefaultsIntoDatabase = InsertHomeDefaultIntoDatabase(homePageRepository),
            insertHomeFeaturesIntoDatabase = InsertHomeFeaturesIntoDatabase(homePageRepository),
        )
    }
}