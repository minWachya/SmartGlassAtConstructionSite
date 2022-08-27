package com.example.safetymanagement2022.data.remote.service

import com.example.safetymanagement2022.data.remote.model.request.CreateBuildingRequest
import com.example.safetymanagement2022.data.remote.model.response.BuildingCreateResponse
import com.example.safetymanagement2022.data.remote.model.response.BuildingDetailResponse
import com.example.safetymanagement2022.data.remote.model.response.ListBuildingResponse
import com.example.safetymanagement2022.data.remote.model.response.ListSmartGlassResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ListService {
    @GET("list/building/list/{user_id}")
    suspend fun getListBuilding(@Path("user_id") userId: String): ListBuildingResponse
    @GET("list/glass/list/{user_id}")
    suspend fun getListGlass(@Path("user_id") userId: String): ListSmartGlassResponse
    @POST("list/building/create/{user_id}")
    suspend fun postBuildingCreate(@Path("user_id") userId: String, @Body body: CreateBuildingRequest): BuildingCreateResponse
    @GET("list/building/detail/{building_id}")
    suspend fun getBuildingDetail(@Path("building_id") buildingId: Int): BuildingDetailResponse
}