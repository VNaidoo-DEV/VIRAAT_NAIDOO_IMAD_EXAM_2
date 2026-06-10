package com.example.imad_exam_2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CampingGearActivity : AppCompatActivity() {
    private val itemNames = arrayOf("Tent","Sleeping Bag","Flashlight","Camp Stove","Water Bottle","First Aid Kit","Backpack","Marshmallows","Compass","Rain-Jacket")
    private val itemCategories = arrayOf("Shelter","Sleeping","Safety","Cooking","Hydration","Medical","Storage","Food","Navigation","Clothing")
    private val itemComments = ArrayList<String>()
    private val itemQuantities = ArrayList<Int>()

    lateinit var txtItem: EditText
    lateinit var txtCategory: EditText
    lateinit var txtQuantity: EditText
    lateinit var txtComment: EditText
    lateinit var btnAdd: Button
    lateinit var btnView: Button
    lateinit var btnClear: Button
    lateinit var txtTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_camping_gear)
        val txtItem = findViewById<EditText>(R.id.txtItem)
        val txtCategory = findViewById<EditText>(R.id.txtCategory)
        val txtQuantity = findViewById<EditText>(R.id.txtQuantity)
        val txtComment = findViewById<EditText>(R.id.txtComment)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnClear = findViewById<Button>(R.id.btnClear)
        Log.d("CampingGearActivity", "onCreate")
        btnAdd.setOnClickListener {

            val itemNameText = txtItem.text.toString().trim()
            val itemCategoryText = txtCategory.text.toString().trim()
            val itemQuantityText = txtQuantity.text.toString().trim()
            val commentText = txtComment.text.toString().trim()

            if (itemNameText.isEmpty() ||
                itemCategoryText.isEmpty() ||
                itemQuantityText.isEmpty() ||
                commentText.isEmpty()
            ) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantity = itemQuantityText.toInt()

            if (quantity <= 0) {
                Toast.makeText(this, "Quantity must be greater than 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ADD DATA FIRST (IMPORTANT FIX)
            itemQuantities.add(quantity)
            itemComments.add(commentText)

            // ✅ NOW calculate total correctly
            var totalItems = 0
            for (q in itemQuantities) {
                totalItems += q
            }

            txtTotal.text = "Total Items Packed: $totalItems"

            // clear fields
            txtItem.text.clear()
            txtCategory.text.clear()
            txtQuantity.text.clear()
            txtComment.text.clear()

            Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show()
        }
        btnView.setOnClickListener {
            val intent = Intent(this, DetailViewScreenActivity::class.java)
            intent.putStringArrayListExtra("itemNames", arrayListOf(*itemNames))
            intent.putStringArrayListExtra("itemCategories", arrayListOf(*itemCategories))
            intent.putStringArrayListExtra("itemComments", itemComments)
            intent.putIntegerArrayListExtra("itemQuantities", itemQuantities)
            startActivity(intent)

        }
        btnClear.setOnClickListener {
            txtItem.text.clear()
            txtCategory.text.clear()
            txtQuantity.text.clear()
            txtComment.text.clear()
            itemQuantities.clear()
            itemComments.clear()
            txtTotal.text = "Total Items Packed: 0"
            Toast.makeText(this, "All fields cleared", Toast.LENGTH_SHORT).show()
        }
    }


}