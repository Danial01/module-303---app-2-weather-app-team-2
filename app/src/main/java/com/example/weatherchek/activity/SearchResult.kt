package com.example.weatherchek.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
    private var background: ImageView? = null
    private var cityDateText: TextView? = null
    private var tempMinMax: TextView? = null
    private val currentDate=SimpleDateFormat("EE dd MMM", Locale.getDefault())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        cityWeather = findViewById(R.id.weatherDescription)
        cityWeatherStatus = findViewById(R.id.weatherStatus)
        background = findViewById(R.id.backgroundImage)
        cityDateText = findViewById(R.id.weatherDate)
        cityWeatherIcon = findViewById(R.id.weatherResultLogo)
        tempMinMax = findViewById(R.id.minMaxTemp)

        val city = intent.getStringExtra(MainActivity.TAG_cityName)
        city?.let {
            getTheWeatherForCity(city)
        }
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
                    if(response.isSuccessful){
                       val listOfWeather = response.body()
                        listOfWeather?.let {
                            val date = Date()
                            cityDateText?.text = currentDate.format(date).toUpperCase(Locale.ROOT)

                            when (it.name) {
                                "London" -> background?.setImageResource(R.drawable.london4)
                                "Oslo" -> background?.setImageResource(R.drawable.oslo2)
                                "Panama" -> background?.setImageResource(R.drawable.panama)
                                "Gothenburg" -> background?.setImageResource(R.drawable.gothenburg)
                            }
                            val description = it.weather?.first()?.description.toString()
                            cityWeather?.text = getString(
                                R.string.weather_description,
                                description[0].toUpperCase(),
                                description.substring(1)
                            )
                            when {
                                it.weather?.first()?.description.toString().contains("rain") ->
                                    cityWeatherIcon?.setImageResource(R.drawable.rain)
                                it.weather?.first()?.description.toString().contains("cloud") ->
                                    cityWeatherIcon?.setImageResource(R.drawable.sunny_cloud2)
                                else -> cityWeatherIcon?.setImageResource(R.drawable.sunny)
                            }

                            cityWeatherStatus?.text =
                                getString(R.string.celsius_temp, it.main?.temp?.roundToInt().toString())
                            tempMinMax?.text = getString(
                                R.string.min_max_temp,
                                it.main?.tempMin?.roundToInt().toString(),
                                it.main?.tempMax?.roundToInt().toString()
                            )
                        }
                    } else {
                        val message = when (response.code()) {
                            500 -> R.string.internal_server_error
                            401 -> R.string.unauthorized
                            403 -> R.string.forbidden
                            404 -> R.string.user_not_found
                            else -> R.string.try_another_user
                        }

                        Toast.makeText(this@SearchResult, message, Toast.LENGTH_LONG).show()
                        Log.e(TAG, getString(message))

                    }}
                    override fun onFailure(call: Call<Cityweather>, t: Throwable) {
                        Toast.makeText(this@SearchResult,
                            getString(R.string.check_error),
                            Toast.LENGTH_LONG).show()
                        Log.e(TAG, getString(R.string.error_log))
                    }
            })
    }

    companion object {
        private val TAG = SearchResult::class.java.simpleName
    }
}