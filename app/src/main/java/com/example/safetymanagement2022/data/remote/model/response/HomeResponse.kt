package com.example.safetymanagement2022.data.remote.model.response

import com.example.safetymanagement2022.common.KEY_ABLE
import com.google.gson.annotations.SerializedName

data class HomeResponse (
    val admin: Int,
    val title: String,
    @SerializedName("is_connected") val isConnected: Int? = KEY_ABLE,
    @SerializedName("issue_list") val issueList: List<SafetyIssue>,
    @SerializedName("building list") val buildingList: List<String>?
)

data class SafetyIssue(
    @SerializedName("raw_data_id") val rawDataId: String,
    @SerializedName("building_name") val BuildingName: String,
    val name: String? = "",
    val picture: String,
    val floor: String,
    val room: String,
    val details: String
)