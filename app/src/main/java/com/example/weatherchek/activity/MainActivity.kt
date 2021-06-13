package com.example.weatherchek.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.weatherchek.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var nameOfCityEdittext: EditText? = null
    private var searchButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameOfCityEdittext = findViewById(R.id.editTextCity)
        searchButton = findViewById(R.id.buttonSearchCity)
       
        searchButton?.setOnClickListener {
            val cityName = nameOfCityEdittext?.text.toString().toLowerCase().trim()
            if (cityName == "oslo" || cityName == "gothenburg" ||
                cityName == "london" || cityName == "panama"
            ) {
                val intent = Intent(this@MainActivity, SearchResult::class.java)
                intent.putExtra("cityName", editTextCity.text.toString())
                startActivity(intent)
            } else {
                editTextCity?.setError("Something went wrong! Enter new city!")
            }
        }
    }
}