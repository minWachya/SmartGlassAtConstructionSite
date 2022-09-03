package com.example.safetymanagement2022.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class GlassCreateRequest(
    @SerializedName("glass_name") val name: String
)
