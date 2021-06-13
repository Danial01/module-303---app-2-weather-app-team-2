package com.example.weatherchek.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.weatherchek.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var nameOfCityEdittext: EditText? = null
    private var searchButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //nameOfCityEdittext = findViewById(R.id.editTextCity)
        //searchButton = findViewById(R.id.buttonSearchCity)

        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.weather_city,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
                 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                 spinner.adapter = adapter
        }
        spinner.onItemSelectedListener= object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val cityName =  parent?.getItemAtPosition(position).toString().trim()
                if (cityName == "Oslo" || cityName == "Gothenburg" ||
                    cityName == "London" || cityName == "Panama"
                ) {
                    val intent = Intent(this@MainActivity, SearchResult::class.java)
                    intent.putExtra("cityName",cityName)
                    startActivity(intent)

                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        }


       /* searchButton?.setOnClickListener {


        }
        }*/
 }


