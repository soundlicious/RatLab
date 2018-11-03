package com.vlab.experiment.ratlabmvvm.data.models.typicode

import com.squareup.moshi.Json

data class PostModel(
    @Json(name = "userId") val userId: Long,
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String
)