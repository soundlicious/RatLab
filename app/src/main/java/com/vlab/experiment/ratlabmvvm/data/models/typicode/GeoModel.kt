package com.vlab.experiment.ratlabmvvm.data.models.typicode

import com.squareup.moshi.Json

data class GeoModel(@Json(name = "lat") val lat: Double, @Json(name = "lng") val lng: Double)
