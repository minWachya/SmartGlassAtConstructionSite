package com.example.safetymanagement2022.repository.home

import android.util.Log
import com.example.safetymanagement2022.di.RetrofitClient
import com.example.safetymanagement2022.model.HomeData
import retrofit2.Call
import retrofit2.Response

class HomeRemoteDataSource(private val userId: String) : HomeDataSource {
    override fun getHomeData(): HomeData? {
        var result: HomeData? = null
        val call = RetrofitClient.serviceApiUser.fetchHome(userId)
        call.enqueue(object : retrofit2.Callback<HomeData> {
            // 응답 성공 시
            override fun onResponse(call: Call<HomeData>, response: Response<HomeData>) {
                if (response.isSuccessful) {
                    val homeData : HomeData = response.body()!!
                    Log.d("mmm1", result.toString())
                    result = homeData
                }
                Log.d("mmm2", result.toString())
                Log.d("mmm21", response.message())
                Log.d("mmm21 headers", response.headers().toString())
                Log.d("mmm21 errorbody", response.errorBody().toString())
                Log.d("mmm21 body", response.body().toString())
                Log.d("mmm21 raw", response.raw().toString())
                Log.d("mmm22", response.isSuccessful.toString())
            }

            // 응답 실패 시
            override fun onFailure(call: Call<HomeData>, t: Throwable) {
                Log.d("mmm home server connect fail", t.message.toString())
            }
        })
        Log.d("mmm result", result.toString())
        return result
    }
}