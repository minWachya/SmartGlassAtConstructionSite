package com.example.safetymanagement2022.model

import com.google.gson.annotations.SerializedName

/*
{
  "building_name": "건물1",
  "max_floor": 3,
  "min_floor": 1,
  "drawing_list": [
    "https://user-images.githubusercontent.com/61674991/179395769-f9da88d4-b423-4b3a-a93d-8919ba0e9ed5.png"
  ],
  "issue_list": [
    {
      "raw_data_id": "crack_user12_220629_1",
      "name": "건물1",
      "picture": "https://user-images.githubusercontent.com/616.jpg",
      "floor": "1층",
      "room": "101호",
      "explain": "4층 입구 오른쪽 위"
    }
  ]
}

*/

data class BuildingDetailData(
    @SerializedName("building_name") val buildingName: String,
    @SerializedName("max_floor") val maxFloor: Int,
    @SerializedName("min_floor") val minFloor: Int,
    @SerializedName("drawing_list") val drawingList: List<String>,
    @SerializedName("issue_list") val issueList: List<SafetyIssue>
)
