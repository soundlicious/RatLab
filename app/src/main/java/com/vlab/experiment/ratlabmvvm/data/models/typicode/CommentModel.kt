package com.vlab.experiment.ratlabmvvm.data.models.typicode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class CommentModel(@Json(name = "postId") val postId : String, @PrimaryKey @Json(name = "id") val id : String,
                        @Json(name = "name") val name : String, @Json(name = "email") val email: String,
                        @Json(name = "body") val body: String)
