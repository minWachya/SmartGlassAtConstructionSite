package com.example.safetymanagement2022.data.remote.service

import com.example.safetymanagement2022.data.remote.model.request.CreateBuildingRequest
import com.example.safetymanagement2022.data.remote.model.request.GlassCreateRequest
import com.example.safetymanagement2022.data.remote.model.response.*
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
    @POST("list/glass/create/{user_id}")
    suspend fun postGlassCreate(@Path("user_id") userId: String, @Body body: GlassCreateRequest): GlassCreateResponse
    @GET("list/building/detail/{building_id}")
    suspend fun getBuildingDetail(@Path("building_id") buildingId: Int): BuildingDetailResponse
}