package com.example.sumission1

import com.example.sumission1.data.response.EventListResponse
import com.example.sumission1.data.response.EventResponse
import com.example.sumission1.data.response.ListEventsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    fun getEventList(@Query("active") active: Int): Call<EventListResponse>

    @GET("events/{id}")
    fun getEvent(@Path("id") id: Int): Call<EventResponse>
}