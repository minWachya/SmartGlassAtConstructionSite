package com.example.safetymanagement2022.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class BuildingDetailResponse(
    @SerializedName("building_name") val buildingName: String,
    @SerializedName("max_floor") val maxFloor: Int,
    @SerializedName("min_floor") val minFloor: Int,
    @SerializedName("drawing_list") val drawingList: List<String>,
    @SerializedName("issue_list") val issueList: List<SafetyIssue>
)