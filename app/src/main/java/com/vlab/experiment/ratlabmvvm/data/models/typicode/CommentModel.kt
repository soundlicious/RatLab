package com.vlab.experiment.ratlabmvvm.data.models.typicode

import com.squareup.moshi.Json

data class CommentModel(@Json(name = "postId") val postId : String, @Json(name = "id") val id : String,
                        @Json(name = "name") val name : String, @Json(name = "email") val email: String,
                        @Json(name = "body") val body: String)
