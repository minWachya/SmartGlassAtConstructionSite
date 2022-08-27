package com.example.safetymanagement2022.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ListSmartGlassResponse(
    @SerializedName("glass_list") val glassList: List<SmartGlass>,
    val admin: Int
)

data class SmartGlass(
    @SerializedName("glass_id") val glassId: String,
    @SerializedName("glass_name") val glassName: String,
    @SerializedName("building_name") val buildingName: String?,
    @SerializedName("user_name") val userName: String?
)