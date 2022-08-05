package com.example.safetymanagement2022.di

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.util.concurrent.TimeUnit

object RetrofitClient {
    var interceptor = HttpLoggingInterceptor { message -> Log.e("", message) }

    private var client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS) //5->10->15 다
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()


    // Home 서버 연결
    private val retrofitUser = Retrofit.Builder()
        .baseUrl("http://ec2-15-164-166-79.ap-northeast-2.compute.amazonaws.com")
        .client(client) //okhttp
        .addConverterFactory(GsonConverterFactory.create())
    val serviceApiUser: ServiceApiInterface by lazy {
        retrofitUser.build().create(ServiceApiInterface::class.java)
    }


}