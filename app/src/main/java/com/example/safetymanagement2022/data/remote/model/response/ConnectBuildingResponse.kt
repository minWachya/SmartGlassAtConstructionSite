package com.example.safetymanagement2022.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ConnectBuilding(
    @SerializedName("building_id") val buildingId: String,
    @SerializedName("building_name") val buildingName: String,
)

data class ConnectBuildingResponse(
    @SerializedName("building_list") val list: List<ConnectBuilding>
)