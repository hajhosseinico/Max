package com.hajhosseinico.max.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hajhosseinico.max.data.local.model.HomeFeatureModelEntity
import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.model.HomePagerModel
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeFeatureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hoeFeatureModel: HomeFeatureModelEntity)

    @Delete
    suspend fun delete(hoeFeatureModel: HomeFeatureModelEntity)

    @Query("SELECT * FROM home_feature_model_table")
    fun getFeatures(): Flow<List<HomeFeatureModelEntity>>
}