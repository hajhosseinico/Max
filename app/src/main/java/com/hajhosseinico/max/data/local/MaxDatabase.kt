package com.hajhosseinico.max.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hajhosseinico.max.data.local.model.HomeFeatureModelEntity
import com.hajhosseinico.max.data.local.model.HomePagerModelEntity
import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.model.HomePagerModel

@Database(entities = [HomePagerModelEntity::class, HomeFeatureModelEntity::class],version = 1,exportSchema = false)

abstract class MaxDatabase : RoomDatabase() {

    abstract val homePagerDao: HomePagerDao
    abstract val homeFeatureDao: HomeFeatureDao

}