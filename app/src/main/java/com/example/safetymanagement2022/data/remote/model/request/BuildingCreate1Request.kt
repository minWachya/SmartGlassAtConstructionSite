package com.example.safetymanagement2022.data.remote.model.request

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

/*
    "building_name": "건물이름1",
    "context": "서울 영등포구 63로 50에 지을 지상 63층 지하 12층 건물",
    "max_floor": 63,
    "min_floor": 12
*/

data class BuildingCreate1Request(
    @SerializedName("building_name") val buildingName: String,
    val context: String,
    @SerializedName("max_floor") val maxFloor: Int,
    @SerializedName("min_floor") val minFloor: Int
)
