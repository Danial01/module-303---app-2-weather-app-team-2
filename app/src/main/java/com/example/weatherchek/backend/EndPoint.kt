package com.example.weatherchek.backend

import com.example.weatherchek.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoint {
    @GET("forecast?id=524901&appid={cityname}")
    fun getweather(@Path("cityname") nameofcity:String): Call<List<Weather>>
}