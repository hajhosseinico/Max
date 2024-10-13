package com.hajhosseinico.max.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hajhosseinico.max.data.local.model.HomePagerModelEntity
import kotlinx.serialization.Serializable

@Serializable
data class HomePagerModel (
    @PrimaryKey val id: Int,
    val backGroundUrl: String,
    val logo: String,
    val title: String,
    val description: String
)

// Converts HomePagerModelEntity to HomePagerModel
fun HomePagerModelEntity.toDomainModel(): HomePagerModel {
    return HomePagerModel(
        id = this.id,
        backGroundUrl = this.backGroundUrl,
        logo = this.logo,
        title = this.title,
        description = this.description
    )
}

// Converts HomePagerModel to HomePagerModelEntity
fun HomePagerModel.toEntityModel(): HomePagerModelEntity {
    return HomePagerModelEntity(
        id = this.id,
        backGroundUrl = this.backGroundUrl,
        logo = this.logo,
        title = this.title,
        description = this.description
    )
}
