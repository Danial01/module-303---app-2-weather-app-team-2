package com.example.weatherchek.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherchek.R
import com.example.weatherchek.backend.RetrofitClient
import com.example.weatherchek.databinding.ActivitySearchResultBinding
import com.example.weatherchek.model.Cityweather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

class SearchResult : AppCompatActivity() {

    lateinit var binding: ActivitySearchResultBinding
    private val currentDate = SimpleDateFormat("EE dd MMM", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myProgressBar.visibility = View.VISIBLE

        val city = intent.getStringExtra(MainActivity.TAG_cityName)
        if (city != null) {
            getTheWeatherForCity(city)
        } else {
            Toast.makeText(this, "Something went wrong! Try again!", Toast.LENGTH_LONG).show()
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
                    if (response.isSuccessful) {
                        val listOfWeather = response.body()
                        listOfWeather?.let {
                            binding.myProgressBar.visibility = View.INVISIBLE
                            val date = Date()
                            binding.weatherDate.text = currentDate.format(date).toUpperCase(Locale.ROOT)

                            when (it.name) {
                                "London" -> binding.backgroundImage.setImageResource(R.drawable.london4)
                                "Oslo" -> binding.backgroundImage.setImageResource(R.drawable.oslo2)
                                "Panama" -> binding.backgroundImage.setImageResource(R.drawable.panama)
                                "Gothenburg" -> binding.backgroundImage.setImageResource(R.drawable.gothenburg)
                            }
                            val description = it.weather?.first()?.description.toString()
                            binding.weatherDescription.text = getString(
                                R.string.weather_description,
                                description[0].toUpperCase(),
                                description.substring(1)
                            )
                            when {
                                it.weather?.first()?.description.toString().contains("rain") ->
                                    binding.weatherResultLogo.setImageResource(R.drawable.rain)
                                it.weather?.first()?.description.toString().contains("cloud") ->
                                    binding.weatherResultLogo.setImageResource(R.drawable.sunny_cloud2)
                                else -> binding.weatherResultLogo.setImageResource(R.drawable.sunny)
                            }

                            binding.weatherStatus.text =
                                getString(R.string.celsius_temp, it.main?.temp?.roundToInt().toString())
                            binding.minMaxTemp.text = getString(
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
                    }
                }
                override fun onFailure(call: Call<Cityweather>, t: Throwable) {
                    Toast.makeText(
                        this@SearchResult,
                        getString(R.string.check_error),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e(TAG, getString(R.string.error_log))
                }
            })
    }

    companion object {
        private val TAG = SearchResult::class.java.simpleName
    }
}
