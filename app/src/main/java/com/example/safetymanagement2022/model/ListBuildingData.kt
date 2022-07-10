package com.example.safetymanagement2022.model

import com.google.gson.annotations.SerializedName

/*

{
  "building_id": "user12_constructionABC_1",
  "name": "건물1",
  "context": "서울 영등포구 63로 50에 지을 지상 63층 지하 12층 건물"
}

*/

data class ListBuildingData(
    @SerializedName("building_list" ) val buildingList: List<Building>
)

data class Building(
    @SerializedName("building_id") val buildingId: String,
    val name: String,
    val context: String
)
