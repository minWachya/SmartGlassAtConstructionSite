package com.example.safetymanagement2022.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class BuildingCreate1Response(
    val message: String,
    @SerializedName("building_id") val buildingId: Int
)
