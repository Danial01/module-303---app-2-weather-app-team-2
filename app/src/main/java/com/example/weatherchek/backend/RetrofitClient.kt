package com.example.weatherchek.backend

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL="http://api.openweathermap.org/data/2.5/"

    val instance:EndPoint by lazy {
        val retrofit=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        retrofit.create(EndPoint::class.java)

    }

}

