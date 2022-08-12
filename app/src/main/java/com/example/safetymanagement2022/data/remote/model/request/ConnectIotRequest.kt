package com.example.safetymanagement2022.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class ConnectIotRequest (
    @SerializedName("user_id") val userId: String,
    @SerializedName("glass_id") val glassId: Int,
    @SerializedName("building_id") val buildingId: Int
    )