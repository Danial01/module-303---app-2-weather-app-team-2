package com.example.weatherchek.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherchek.R
import com.example.weatherchek.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ArrayAdapter.createFromResource(
            this,
            R.array.weather_city,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }
        binding.spinner.onItemSelectedListener = this
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        parent.setSelection(0)
        when (parent.getItemAtPosition(pos)) {
            "Oslo,NO", "London,GB",
            "Panama,PA", "Gothenburg,SE" -> {
                val cityName = parent.getItemAtPosition(pos).toString()
                val intent = Intent(this@MainActivity, SearchResult::class.java)
                intent.putExtra(TAG_cityName, cityName)
                startActivity(intent)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    companion object {
        const val TAG_cityName = "cityName"
    }
}
