package com.example.weatherchek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var nameOfCityEdittext:EditText?=null
    private var searchButton:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameOfCityEdittext=findViewById(R.id.editTextCity)
        searchButton=findViewById(R.id.buttonSearchCity)

        searchButton?.setOnClickListener {
            val intent = Intent(this, searchresult::class.java)
            startActivity(intent)
        }

    }
}