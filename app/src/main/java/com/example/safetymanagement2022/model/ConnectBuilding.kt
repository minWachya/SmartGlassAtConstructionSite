package com.example.safetymanagement2022.model

import com.google.gson.annotations.SerializedName

data class ConnectBuilding(
    @SerializedName("building_id") val buildingId: String,
    @SerializedName("building_name") val buildingName: String,
)

data class ConnectBuildingData(
    @SerializedName("building_list") val list: List<ConnectBuilding>
    )