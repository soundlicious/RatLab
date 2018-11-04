package com.vlab.experiment.ratlabmvvm.data.models.typicode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class AddressModel(@PrimaryKey(autoGenerate = true) var id: Long?,
    @Json(name = "street") val street: String,
    @Json(name = "suite") val suite: String,
    @Json(name = "city") val city: String,
    @Json(name = "zipcode") val zipcode: String,
    @Json(name = "geo") val geo: GeoModel
)
