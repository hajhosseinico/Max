package com.hajhosseinico.max.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_pager_model_table")
data class HomePagerModelEntity (
    @PrimaryKey val id: Int,
    val backGroundUrl: String,
    val logo: String,
    val title: String,
    val description: String
)

