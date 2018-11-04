package com.vlab.experiment.ratlabmvvm.data.models.typicode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class PhotoModel(
    @Json(name = "albumId") val albumId: String,
    @PrimaryKey @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String,
    @Json(name = "thumbnailUrl") val thumbnailUrl: String
)
