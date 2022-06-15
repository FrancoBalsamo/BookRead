package com.frabasoft.bookread

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.frabasoft.bookread.lectortmo.LectorTmo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTMo = findViewById<Button>(R.id.btnTMO)
        btnTMo.setOnClickListener {
            val abrirTMO = Intent(this, LectorTmo::class.java)
            startActivity(abrirTMO)
        }
    }
}