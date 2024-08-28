package com.example.sumission1

import com.example.sumission1.data.response.EventListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    fun getEventList(@Query("active") active: Int): Call<EventListResponse>
}