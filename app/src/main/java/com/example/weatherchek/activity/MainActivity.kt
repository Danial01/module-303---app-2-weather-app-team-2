package com.example.weatherchek.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.weatherchek.R
import com.example.weatherchek.backend.RetrofitClient
import com.example.weatherchek.model.Weather
import com.example.weatherchek.model.weathercity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var nameOfCityEdittext:EditText?=null
    private var searchButton:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameOfCityEdittext=findViewById(R.id.editTextCity)
        searchButton=findViewById(R.id.buttonSearchCity)

        searchButton?.setOnClickListener {
            val intent = Intent(this, searchresult::class.java)
            startActivity(intent)
            getTheWeatherForCity("gothenburg")
        }

    }
    private fun getTheWeatherForCity(city:String){
        RetrofitClient
            .instance
            .getweather(city)
            .enqueue( object : Callback<List<Weather>>{
                override fun onResponse(
                    call: Call<List<Weather>>,
                    response: Response<List<Weather>>
                ) {
                    val listOfWeather=response.body() as ArrayList<Weather>
                }

                override fun onFailure(call: Call<List<Weather>>, t: Throwable) {
                    Log.e(TAG,"Not getting Data")
                }
            })
    }
    companion object {
        private val TAG=MainActivity::class.java.simpleName
    }
}