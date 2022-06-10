package com.frabasoft.bookread.splashdetails

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.frabasoft.bookread.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashIntent = Intent(this, MainActivity::class.java)
        startActivity(splashIntent)
        finish()
    }
}