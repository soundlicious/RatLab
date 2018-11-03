package com.vlab.experiment.ratlabmvvm.data.models.typicode

import com.squareup.moshi.Json

data class AlbumModel(
    @Json(name = "userId") val userId: String,
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String
)
