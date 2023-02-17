package com.example.circlesmodule.src

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.circlesmodule.R

class CirclesModuleActivity : AppCompatActivity() {
    private lateinit var calculateButton: Button
    private lateinit var editTextX1: EditText
    private lateinit var editTextY1: EditText
    private lateinit var editTextR1: EditText
    private lateinit var editTextX2: EditText
    private lateinit var editTextY2: EditText
    private lateinit var editTextR2: EditText
    private lateinit var outputTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circlesmodule)
        calculateButton = findViewById(R.id.button)
        editTextX1 = findViewById(R.id.x1_input)
        editTextY1 = findViewById(R.id.y1_input)
        editTextR1 = findViewById(R.id.r1_input)
        editTextX2 = findViewById(R.id.x2_input)
        editTextY2 = findViewById(R.id.y2_input)
        editTextR2 = findViewById(R.id.r2_input)
        outputTextView = findViewById(R.id.textView3)

        calculateButton.setOnClickListener {
            calculate()
            hideKeyboard(View(this))
        }

        assert(
            supportActionBar != null
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun calculate() {
        outputTextView.text = ""

        try {
            val x1 = editTextX1.text.toString().toDouble()
            val y1 = editTextY1.text.toString().toDouble()
            val r1 = editTextR1.text.toString().toDouble()
            val x2 = editTextX2.text.toString().toDouble()
            val y2 = editTextY2.text.toString().toDouble()
            val r2 = editTextR2.text.toString().toDouble()

            outputTextView.text = CirclesModuleMath.circles(x1, y1, r1, x2, y2, r2)

        } catch (exception: NumberFormatException) {
            Toast.makeText(this, "Unexpected input: ${exception.message}\"", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}