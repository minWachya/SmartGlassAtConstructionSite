package com.example.safetymanagement2022.model

import com.google.gson.annotations.SerializedName

/*

{
  "admin": 1, // user일 땐 0
  "title: "새 이슈", // user일 땐 "내가 발견한 안전 문제"
  "issue_list": [
    {
      "raw_data_id": "crack_user12_220629_1",
      "name": "건물1",
      "picture": "https://user-images.githubusercontent.com/61674991/178135390-87301b37-071e-4c17-8378-0f3690a363ea.png",
      "floor": "4층",
      "room": "101호",
      "details": "4층 입구 오른쪽 위"
    }
  ]
}
*/

data class HomeData(
    val admin: Int,
    val title: String,
    @SerializedName("issue_list") val issueList: List<SafetyIssue>,
    @SerializedName("building_list") val buildingList: List<String>?
)

data class SafetyIssue(
    @SerializedName("raw_data_id") val rawDataId: String,
    val name: String,
    val picture: String,
    val floor: String,
    val room: String,
    val details: String
)