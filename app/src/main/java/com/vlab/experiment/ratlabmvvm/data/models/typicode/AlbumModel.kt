package com.vlab.experiment.ratlabmvvm.data.models.typicode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class AlbumModel(
    @Json(name = "userId") val userId: String,
    @PrimaryKey @Json(name = "id") val id: String,
    @Json(name = "title") val title: String
)
