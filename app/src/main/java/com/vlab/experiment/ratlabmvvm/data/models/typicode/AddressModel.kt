package com.vlab.experiment.ratlabmvvm.data.models.typicode

import com.squareup.moshi.Json

data class AddressModel(
    @Json(name = "street") val street: String,
    @Json(name = "suite") val suite: String,
    @Json(name = "city") val city: String,
    @Json(name = "zipcode") val zipcode: String,
    @Json(name = "geo") val geo: GeoModel
)
