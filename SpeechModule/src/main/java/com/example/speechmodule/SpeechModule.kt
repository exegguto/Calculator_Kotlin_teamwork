package com.example.speechmodule

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

class SpeechModule : AppCompatActivity() {
    private lateinit var calculateButton: Button
    private lateinit var outputTextView: TextView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_module)
        calculateButton = findViewById(R.id.button)
        outputTextView = findViewById(R.id.textView3)
        editText = findViewById(R.id.editTextNumberDecimal)

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
            val x1 = editText.text.toString().toLong()

            outputTextView.text = SpeechModuleMath.speechModuleProcess(x1)
        } catch (exception: NumberFormatException) {
            Toast.makeText(this, "Unexpected input: ${exception.message}\"", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
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