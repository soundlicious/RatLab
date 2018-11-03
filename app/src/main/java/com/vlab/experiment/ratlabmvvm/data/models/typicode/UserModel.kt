package com.vlab.experiment.ratlabmvvm.data.models.typicode

import com.squareup.moshi.Json

// Too many arguments in the constructor, email, phone and website should be in a Contact class
data class UserModel(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "address") val address: AddressModel,
    @Json(name = "phone") val phone: String,
    @Json(name = "website") val website: String,
    @Json(name = "company") val company: CompanyModel
)
