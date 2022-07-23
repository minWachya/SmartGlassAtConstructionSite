package com.example.safetymanagement2022.model

import com.google.gson.annotations.SerializedName

/*

"glass_list": [
    {
      "glass_id": "glass_111",
      "glass_name": "글래스1",
      "enable": 1
    }
 ]
*/

data class ConnectSmartGlass(
    @SerializedName("glass_id") val smartGlassId: String,
    @SerializedName("glass_name") val smartGlassName: String,
    val enable: Int
)

data class ConnectGlassData(
    @SerializedName("glass_list") val list: List<ConnectSmartGlass>
    )