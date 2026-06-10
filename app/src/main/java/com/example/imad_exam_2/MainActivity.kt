package com.example.imad_exam_2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnStart: Button
    lateinit var btnExit: Button
    lateinit var txtTitle: TextView
    lateinit var imgLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate")
        val btnStart = findViewById<Button>(R.id.btnStart)
        Log.d("VAR CREATED", "onCreate")
        val btnExit = findViewById<Button>(R.id.btnExit)
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        val imgLogo = findViewById<ImageView>(R.id.imgLogo)
        btnStart.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                Log.d("Handler", "onCreate")
                imgLogo.visibility = View.VISIBLE
                txtTitle.visibility = View.VISIBLE
                val intent = Intent(this, CampingGearActivity::class.java)
                startActivity(intent)
            }, 3000)


        }
        btnExit.setOnClickListener {
            finishAffinity()
        }

    }
}