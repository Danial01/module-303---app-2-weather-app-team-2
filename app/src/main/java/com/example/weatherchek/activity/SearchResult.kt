package com.example.weatherchek.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.weatherchek.R
import com.example.weatherchek.backend.RetrofitClient
import com.example.weatherchek.model.Cityweather
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResult : AppCompatActivity() {

    private var cityWeather:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        cityWeather=findViewById(R.id.cityWeather)
        val city=intent.getStringExtra("cityname")
        getTheWeatherForCity(city.toString())

    }
    private fun getTheWeatherForCity(city:String){
        RetrofitClient
            .instance
            .getWeather(city,"d4bd058fbe8bd27d234fe83a6b74b736")
            .enqueue( object : Callback<Cityweather> {
                override fun onResponse(
                    call: Call<Cityweather>,
                    response: Response<Cityweather>
                ) {
                    val listOfWeather=response.body()
                    listOfWeather?.let {
                        cityWeather?.text=it.weather?.first()?.description+" "+it.name
                        /*val intent = Intent(this@MainActivity, SearchResult::class.java)
                        intent.(KEY_REPOSITORY_DATA,it)
                        startActivity(intent)*/

                    }
                }

                override fun onFailure(call: Call<Cityweather>, t: Throwable) {
                    Log.e(SearchResult.TAG,"Not getting Data")
                }
            })
    }
    companion object {
        private val TAG=MainActivity::class.java.simpleName
    }
}