package com.example.weatherchek.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.weatherchek.R
import com.example.weatherchek.backend.RetrofitClient
import com.example.weatherchek.model.Cityweather
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var nameOfCityEdittext:EditText?=null
    private var searchButton:Button?=null
    private var weatherForCity:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameOfCityEdittext=findViewById(R.id.editTextCity)
        searchButton=findViewById(R.id.buttonSearchCity)
        weatherForCity=findViewById(R.id.cityWeatherTextview)

        searchButton?.setOnClickListener {
            val cityName=nameOfCityEdittext?.text.toString().trim().toLowerCase()
            if (cityName=="oslo" || cityName=="gothenburg" ||
                    cityName=="london" || cityName=="panama") {

                val intent = Intent(this@MainActivity, SearchResult::class.java)
                intent.putExtra("cityname",editTextCity.text.toString())
                startActivity(intent)


            } else {
                editTextCity?.setError("Something went wrong! Enter new city!")
            }
            }


    }


}