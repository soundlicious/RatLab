package com.vlab.experiment.ratlabmvvm.data.models.typicode

import com.squareup.moshi.Json

data class PhotoModel(
    @Json(name = "albumId") val albumId: String,
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String,
    @Json(name = "thumbnailUrl") val thumbnailUrl: String
)
