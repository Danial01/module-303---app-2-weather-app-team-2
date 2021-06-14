package com.example.weatherchek.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.weatherchek.R


class SplashScreen : AppCompatActivity() {
    private var splashScreenLogo:ImageView?=null
    private var splashScreenString:ImageView?=null
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        splashScreenLogo = findViewById(R.id.logosplash)
        splashScreenString=findViewById(R.id.splashString)

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.splash_slide_in_top)

        //bottomAnim = AnimationUtils.loadAnimation(this, R.anim.splash_slide_in_bottom)



        splashScreenLogo?.setAnimation(topAnim)
        splashScreenString?.setAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_slide_in_bottom))
        handler=Handler()
        handler.postDelayed({

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

            finish()
        },5000)

    }

}

