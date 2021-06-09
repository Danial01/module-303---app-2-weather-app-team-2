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
import kotlin.math.roundToInt

class SearchResult : AppCompatActivity() {

    private var cityWeather: TextView? = null
    private var cityWeatherStatus: TextView? = null
    private var cityWeatherIcon: ImageView? = null
    private var cityTempIcon: ImageView? = null
    private var cityTempText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        cityWeather = findViewById(R.id.cityWeather)
        cityWeatherStatus = findViewById(R.id.weatherStatus)
        cityWeatherIcon = findViewById(R.id.weatherIcon)
        cityTempIcon = findViewById(R.id.cityTemp)
        cityTempText = findViewById(R.id.cityTempStat)
        val city = intent.getStringExtra("cityname")

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
                        cityWeather?.text = it.name
                        if (it.weather?.first()?.description.toString().contains("rain")) {
                            cityWeatherIcon?.setImageResource(R.drawable.rain)
                        } else if (it.weather?.first()?.description.toString().contains("cloud")) {
                            cityWeatherIcon?.setImageResource(R.drawable.cloud)
                        } else {
                            cityWeatherIcon?.setImageResource(R.drawable.clearsky)
                        }
                        cityWeatherStatus?.text = it.weather?.first()?.description
                        if (it.main?.temp!! > 15.0) {
                            cityTempIcon?.setImageResource(R.drawable.warmtemp)

                        } else {
                            cityTempIcon?.setImageResource(R.drawable.coldtemp)
                        }
                        cityTempText?.text = it.main?.temp.roundToInt().toString() + " °C"
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