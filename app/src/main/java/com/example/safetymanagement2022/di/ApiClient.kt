package com.example.safetymanagement2022.di

import com.example.safetymanagement2022.model.ConnectBuildingData
import com.example.safetymanagement2022.model.ConnectGlassData
import com.example.safetymanagement2022.model.HomeData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    // home
    @GET("/home/user/{user_id}")
    suspend fun fetchHome(@Path("user_id") userId: String): HomeData
    @GET("/home/user/list/connect/1/{user_id}")
    suspend fun fetchConnectGlass(@Path("user_id") userId: String): ConnectGlassData
    @GET("/home/user/list/connect/2/{user_id}")
    suspend fun fetchConnectBuilding(@Path("user_id") userId: String): ConnectBuildingData

    // ApiClient 객체 생성
    companion object {

        private const val baseUrl = "http://ec2-15-164-166-79.ap-northeast-2.compute.amazonaws.com"

        fun create(): ApiClient {
            // okhttp에서 로그 확인용 client
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            // addConverterFactory: http 응답 결과을 프로젝트에서 사용하는 객체로 변환하는 방법
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}