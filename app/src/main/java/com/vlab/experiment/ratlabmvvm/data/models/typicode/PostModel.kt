package com.vlab.experiment.ratlabmvvm.data.models.typicode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class PostModel(
    @Json(name = "userId") val userId: Long,
    @PrimaryKey @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String
)