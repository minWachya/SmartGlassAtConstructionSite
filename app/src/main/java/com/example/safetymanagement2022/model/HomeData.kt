package com.example.safetymanagement2022.model

import com.google.gson.annotations.SerializedName

/*

{
   "raw_data_id": "crack_user12_220629_1",
   "name": "건물1",
   "picture": "https://user-images.githubusercontent.com/616.jpg",
   "floor": "4층",
   "room": "101호",
   "details": "4층 입구 오른쪽 위"
}

*/

data class HomeData(
    @SerializedName("issue_list") val issueList: List<SafetyIssue>
)

data class SafetyIssue(
    @SerializedName("raw_data_id") val rawDataId: String,
    val name: String,
    val picture: String,
    val floor: String,
    val room: String,
    val details: String
)