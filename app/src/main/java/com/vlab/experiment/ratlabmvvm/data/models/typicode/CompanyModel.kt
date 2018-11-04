package com.vlab.experiment.ratlabmvvm.data.models.typicode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class CompanyModel(@PrimaryKey(autoGenerate = true) var id: Long?,
                        @Json(name = "name") val name: String,
                        @Json(name = "catchphrase") val catchphrase: String?,
                        @Json(name = "bs") val bs: String
)
