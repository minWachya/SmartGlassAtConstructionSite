package com.example.safetymanagement2022.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message: String,
    @SerializedName("user_id") val userId: String
)
