package com.example.safetymanagement2022.data.remote.service

import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.data.remote.model.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeService {
    @GET("/home/user/{user_id}")
    suspend fun getHome(@Path("user_id") userId: String): HomeResponse
    @GET("/home/user/list/connect/1/{user_id}")
    suspend fun getConnectGlass(@Path("user_id") userId: String): ConnectGlassResponse
    @GET("/home/user/list/connect/2/{user_id}")
    suspend fun getConnectBuilding(@Path("user_id") userId: String): ConnectBuildingResponse
    @POST("/home/user/connect/iot")
    suspend fun postConnectIot(@Body body: ConnectIotRequest): ConnectIotResponse
    @GET("/home/user/disconnect/iot/{user_id}")
    suspend fun getDisConnectIot(@Path("user_id") userId: String): DisConnectResponse

}
