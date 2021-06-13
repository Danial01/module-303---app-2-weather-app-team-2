package com.example.weatherchek.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherchek.R
import com.example.weatherchek.backend.RetrofitClient
import com.example.weatherchek.model.Cityweather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class SearchResult : AppCompatActivity() {

    private var cityWeather: TextView? = null
    private var cityWeatherIcon: ImageView? = null
    private var cityWeatherStatus: TextView? = null
    private var background:ImageView?=null
    private var cityDateText: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        cityWeather = findViewById(R.id.weatherDescription)
        cityWeatherStatus = findViewById(R.id.weatherStatus)
        background=findViewById(R.id.backgroundImage)
        cityDateText = findViewById(R.id.weatherDate)
        cityWeatherIcon=findViewById(R.id.weatherResultLogo)


        val city = intent.getStringExtra("cityName")


        getTheWeatherForCity(city.toString())
    }

    private fun getTheWeatherForCity(city: String) {
        RetrofitClient
            .instance
            .getWeather(city, "d4bd058fbe8bd27d234fe83a6b74b736", "metric")
            .enqueue(object : Callback<Cityweather> {
                override fun onResponse(
                    call: Call<Cityweather>,
                    response: Response<Cityweather>
                ) {
                    val listOfWeather = response.body()
                    listOfWeather?.let {
                        val date=Date()
                        cityDateText?.text= SimpleDateFormat("EE MMMM yyyy").format(date)

                        when(it.name){
                            "London"->background?.setImageResource(R.drawable.london3)
                            "Oslo"->background?.setImageResource(R.drawable.oslo2)
                            "Panama"->background?.setImageResource(R.drawable.panama)
                            "Gothenburg"->background?.setImageResource(R.drawable.gothenburg)
                        }

                        cityWeather?.text = it.weather?.first()?.description
                        if (it.weather?.first()?.description.toString().contains("rain")) {
                            cityWeatherIcon?.setImageResource(R.drawable.rain)
                        } else if (it.weather?.first()?.description.toString().contains("cloud")) {
                            cityWeatherIcon?.setImageResource(R.drawable.sunny_cloud2)
                        } else {
                            cityWeatherIcon?.setImageResource(R.drawable.sunny)
                        }
                        cityWeatherStatus?.text = it.main?.temp?.roundToInt().toString() + " °C"
                    }
                }
                override fun onFailure(call: Call<Cityweather>, t: Throwable) {
                    Log.e(SearchResult.TAG, "Not getting Data")
                }
            })
    }
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}