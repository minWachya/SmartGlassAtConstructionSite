package com.example.safetymanagement2022.di

import com.example.safetymanagement2022.model.HomeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApiInterface {
    // Home 서버
    // Home  화면
    @GET("/home/user/{user_id}")
    fun fetchHome(@Path("user_id") userId: String): Call<HomeData>
}