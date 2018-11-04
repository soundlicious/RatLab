package com.vlab.experiment.ratlabmvvm.data.models.typicode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class GeoModel(@PrimaryKey(autoGenerate = true) var id: Long?, @Json(name = "lat") val lat: Double, @Json(name = "lng") val lng: Double)

