package com.example.safetymanagement2022.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("user_id") val id: String,
    @SerializedName("user_pw") val pw: String
)
