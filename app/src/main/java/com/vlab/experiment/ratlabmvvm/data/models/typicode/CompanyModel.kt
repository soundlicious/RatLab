package com.vlab.experiment.ratlabmvvm.data.models.typicode

import com.squareup.moshi.Json

data class CompanyModel(
    @Json(name = "name") val name: String,
    @Json(name = "catchphrase") val catchphrase: String?,
    @Json(name = "bs") val bs: String
)
