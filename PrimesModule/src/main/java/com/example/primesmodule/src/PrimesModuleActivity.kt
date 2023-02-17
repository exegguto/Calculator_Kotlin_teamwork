package com.example.primesmodule.src

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.loggername.src.Logger
import com.example.primesmodule.R
import kotlin.math.sqrt

class PrimesModuleActivity : AppCompatActivity() {
    private lateinit var calculateButton: Button
    private lateinit var numEditText: EditText
    private lateinit var outputTextView: TextView
    private lateinit var orderSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primesmodule)
        calculateButton = findViewById(R.id.button)
        numEditText = findViewById(R.id.editTextNumberDecimal)
        outputTextView = findViewById(R.id.textView3)
        orderSpinner = findViewById(R.id.spinner)
        calculateButton.setOnClickListener {
            Logger.log(
                this.filesDir.toString(),
                Thread.currentThread().stackTrace[2].methodName,
                "Click Button in CirclesModuleActivity"
            )
            hideKeyboard(View(this))
            calculate()
        }
        assert(
            supportActionBar != null
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.order,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        orderSpinner.adapter = adapter
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

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) if (n % i == 0) return false
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun primeNumbers(number: Int, order: String = "Lower") {
        val set = mutableListOf<Int>()
        var n = number
        if (order == "Lower") {
            while (n > 0) {
                set.add(n)
                n /= 10
            }
            set.reverse()
        } else {
            var num = 0
            while (n > 0) {
                num = num * 10 + n % 10
                set.add(num)
                n /= 10
            }
        }
        for (num in set) {
            val temp = outputTextView.text.toString()
            if (isPrime(num)) outputTextView.text = temp + "$num: prime\n"
            else outputTextView.text = temp + "$num\n"
        }
    }

    private fun calculate() {
        outputTextView.text = ""
        val order = orderSpinner.selectedItem.toString()
        try {
            val num = numEditText.text.toString().toInt()
            if (order == "Higher") primeNumbers(num, "Higher")
            else primeNumbers(num)
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Unexpected input: ${e.message}\"", Toast.LENGTH_SHORT).show()
        }
    }
}