package com.example.weatherchek.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.weatherchek.R

class SplashScreen : AppCompatActivity() {
    private var splashScreenLogo: ImageView? = null
    private var splashScreenString: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        splashScreenLogo = findViewById(R.id.logoSplash)
        splashScreenString = findViewById(R.id.splashString)

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.splash_slide_in_top)
        splashScreenLogo?.animation = topAnim
        splashScreenString?.animation = AnimationUtils.loadAnimation(
            this,
            R.anim.splash_slide_in_bottom
        )
        val timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }
        }
        timer.start()
    }
}

