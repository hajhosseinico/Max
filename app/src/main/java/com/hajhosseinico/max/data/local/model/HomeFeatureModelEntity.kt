package com.hajhosseinico.max.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "home_feature_model_table")
data class HomeFeatureModelEntity (
    @PrimaryKey val id: Int,
    val backgroundUrl: String,
    val logo: String,
    val title: String,
    val description: String
)
