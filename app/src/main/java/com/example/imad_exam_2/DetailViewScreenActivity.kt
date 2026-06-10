package com.example.imad_exam_2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailViewScreenActivity : AppCompatActivity() {
    lateinit var txtOutput: TextView
    lateinit var btnBack: Button
    lateinit var btnReview: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_view_screen)

        val txtOutput = findViewById<TextView>(R.id.txtOutput)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val itemNames = intent.getStringArrayListExtra("itemNames") ?: arrayListOf()
        val itemCategories = intent.getStringArrayListExtra("itemCategories") ?: arrayListOf()
        val itemComments = intent.getStringArrayListExtra("itemComments") ?: arrayListOf()
        val itemQuantities = intent.getIntegerArrayListExtra("itemQuantities") ?: arrayListOf()
        btnReview.setOnClickListener {
            if (itemNames.isEmpty()) {
                txtOutput.text = "No items added yet."
                return@setOnClickListener
            }

            val size = minOf(
                itemNames.size,
                itemCategories.size,
                itemComments.size,
                itemQuantities.size
            )

            val display = StringBuilder()

            for (i in 0 until size) {
                display.append(
                    "Item Name: ${itemNames[i]}\n" +
                            "Category: ${itemCategories[i]}\n" +
                            "Quantity: ${itemQuantities[i]}\n" +
                            "Comment: ${itemComments[i]}\n\n"
                )
            }

            txtOutput.text = display.toString()

            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}
