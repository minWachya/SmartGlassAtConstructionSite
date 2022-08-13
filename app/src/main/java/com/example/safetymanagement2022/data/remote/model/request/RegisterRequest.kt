package com.example.safetymanagement2022.data.remote.model.request

import com.google.gson.annotations.SerializedName

/*
 "user_id": "aaa123",
  "user_pw": "asdfgasdfg",
  "name": "이민영",
  "company_name": "ㅇㅇ건설",
  "admin": 0
*/

data class RegisterRequest(
    @SerializedName("user_id") val id: String,
    @SerializedName("user_pw") val pw: String,
    val name: String,
    @SerializedName("company_name") val companyName: String,
    val admin: Int
)
