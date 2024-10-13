package com.hajhosseinico.max.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hajhosseinico.max.data.local.model.HomePagerModelEntity
import com.hajhosseinico.max.domain.model.HomePagerModel
import kotlinx.coroutines.flow.Flow

@Dao
interface HomePagerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(homePagerModel: HomePagerModelEntity)

    @Delete
    suspend fun delete(homePagerModel: HomePagerModelEntity)

    @Query("SELECT * FROM home_pager_model_table")
    fun getDashList(): Flow<List<HomePagerModelEntity>>
}