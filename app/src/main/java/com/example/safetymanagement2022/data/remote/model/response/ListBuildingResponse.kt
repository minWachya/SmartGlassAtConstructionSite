package com.example.safetymanagement2022.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ListBuildingResponse(
    @SerializedName("building_list") val buildingList: List<Building>,
    val admin: Int
)

data class Building(
    @SerializedName("building_id") val buildingId: String,
    val name: String,
    val context: String
)