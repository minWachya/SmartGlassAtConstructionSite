package com.example.safetymanagement2022.model

import com.google.gson.annotations.SerializedName

/*

{
   "glass_id": "glass1",
   "glass_name": "user12_constructionABC_1",
   "building_name": "건물1",
   "user_name": "이민영"
}

*/

data class ListSmartGlassData(
    @SerializedName("glass_list") val glassList: List<SmartGlass>
)

data class SmartGlass(
    @SerializedName("glass_id") val glassId: String,
    @SerializedName("glass_name") val glassName: String,
    @SerializedName("building_name") val buildingName: String,
    @SerializedName("user_name") val userName: String
)