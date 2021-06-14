package com.example.weatherchek.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.weatherchek.R
import com.example.weatherchek.model.Cityweather
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.weather_city,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener=this
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        parent.setSelection(0)
        when(parent.getItemAtPosition(pos)) {
            "Oslo,NO","London,GB",
            "Panama,PA","Gothenburg,SE"-> {
                val cityName = parent.getItemAtPosition(pos).toString()
                val intent = Intent(this@MainActivity, SearchResult::class.java)
                intent.putExtra("cityName", cityName)
                startActivity(intent)
            }
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}


