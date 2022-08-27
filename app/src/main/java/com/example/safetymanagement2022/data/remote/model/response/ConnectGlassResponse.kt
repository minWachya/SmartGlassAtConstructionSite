package com.example.safetymanagement2022.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ConnectSmartGlass(
    @SerializedName("glass_id") val smartGlassId: String,
    @SerializedName("glass_name") val smartGlassName: String,
    val enable: Int
)

data class ConnectGlassResponse(
    @SerializedName("glass_list") val list: List<ConnectSmartGlass>
)