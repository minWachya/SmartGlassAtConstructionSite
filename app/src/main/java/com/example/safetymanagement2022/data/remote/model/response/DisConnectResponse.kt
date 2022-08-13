package com.example.safetymanagement2022.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DisConnectResponse(
    @SerializedName("connect_message") val message: String
)
