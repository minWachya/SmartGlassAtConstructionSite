package com.example.safetymanagement2022.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ConnectIotResponse(
    @SerializedName("connect_message") val connectMessage: String
)
