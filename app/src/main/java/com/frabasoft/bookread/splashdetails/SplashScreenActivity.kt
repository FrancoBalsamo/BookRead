package com.frabasoft.bookread.splashdetails

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.frabasoft.bookread.MainActivity
import com.frabasoft.bookread.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val ivSplash = findViewById<ImageView>(R.id.idIVSplash)
        val splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        ivSplash.startAnimation(splashAnimation)
        val splashIntent = Intent(this, MainActivity::class.java)
        splashAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(splashIntent)
                finish()
            }
        })
    }
}