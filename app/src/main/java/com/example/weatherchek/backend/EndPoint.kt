package com.example.weatherchek.backend

import com.example.weatherchek.model.Cityweather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    @GET("/data/2.5/weather")
    fun getWeather(
        @Query("q") q:String,
        @Query("appid") appid :String,
        @Query("units") units:String
    ): Call<Cityweather>
}