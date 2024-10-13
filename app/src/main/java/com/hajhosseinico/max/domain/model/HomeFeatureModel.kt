package com.hajhosseinico.max.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hajhosseinico.max.data.local.model.HomeFeatureModelEntity
import kotlinx.serialization.Serializable

@Serializable
data class HomeFeatureModel (
    @PrimaryKey val id: Int,
    val backgroundUrl: String,
    val logo: String,
    val title: String,
    val description: String
)


// Converts HomeFeatureModelEntity to HomeFeatureModel
fun HomeFeatureModelEntity.toDomainModel(): HomeFeatureModel {
    return HomeFeatureModel(
        id = this.id,
        backgroundUrl = this.backgroundUrl,
        logo = this.logo,
        title = this.title,
        description = this.description
    )
}

// Converts HomeFeatureModel to HomeFeatureModelEntity
fun HomeFeatureModel.toEntityModel(): HomeFeatureModelEntity {
    return HomeFeatureModelEntity(
        id = this.id,
        backgroundUrl = this.backgroundUrl,
        logo = this.logo,
        title = this.title,
        description = this.description
    )
}
