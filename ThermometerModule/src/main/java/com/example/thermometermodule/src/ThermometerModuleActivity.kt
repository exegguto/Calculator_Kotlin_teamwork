package com.example.thermometermodule.src

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.thermometermodule.R
import com.example.loggername.src.Logger

class ThermometerModuleActivity : AppCompatActivity() {
    private var season = Season.WINTER
    private var temperatureType = TemperatureType.CELSIUS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thermometermodule)

        val calculateButton = findViewById<Button>(R.id.button)
        val numEditTextTemperature = findViewById<EditText>(R.id.editTextNumberDecimal)
        val numEditTextHumidity = findViewById<EditText>(R.id.editTextNumberDecimal2)
        val outputTextView = findViewById<TextView>(R.id.textView3)

        calculateButton.setOnClickListener {

            Logger.log(this.filesDir.toString(), Thread.currentThread().stackTrace[2].methodName, "Click Button")
            var celsius = 0.0
            if (numEditTextTemperature.text.toString() != "")
                celsius = numEditTextTemperature.text.toString().toDouble()
            else numEditTextTemperature.setText("0")

            var humidity = 0.0
            if (numEditTextTemperature.text.toString() != "")
                humidity = numEditTextHumidity.text.toString().toDouble()
            else numEditTextHumidity.setText("0")

            outputTextView.text =
                Temperature(celsius).calculateTemp(season, temperatureType, humidity)
            hideKeyboard(View(this))
        }

        assert(
            supportActionBar != null
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val radioButtonSummer = findViewById<RadioButton>(R.id.radioButtonSummer)
        radioButtonSummer.setOnClickListener(radioButtonClickListenerSeason)

        val radioButtonWinter = findViewById<RadioButton>(R.id.radioButtonWinter)
        radioButtonWinter.setOnClickListener(radioButtonClickListenerSeason)

        val radioButtonCelsius = findViewById<RadioButton>(R.id.radioButtonCelsius)
        radioButtonCelsius.setOnClickListener(radioButtonClickListenerTemperate)

        val radioButtonFahrenheit = findViewById<RadioButton>(R.id.radioButtonFahrenheit)
        radioButtonFahrenheit.setOnClickListener(radioButtonClickListenerTemperate)

        val radioButtonKelvin = findViewById<RadioButton>(R.id.radioButtonKelvin)
        radioButtonKelvin.setOnClickListener(radioButtonClickListenerTemperate)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private var radioButtonClickListenerSeason =
        View.OnClickListener { v ->
            val rb = v as RadioButton
            when (rb.id) {
                R.id.radioButtonSummer -> season = Season.SUMMER
                R.id.radioButtonWinter -> season = Season.WINTER
                else -> {}
            }
        }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private var radioButtonClickListenerTemperate =
        View.OnClickListener { v ->
            val rb = v as RadioButton
            when (rb.id) {
                R.id.radioButtonCelsius -> temperatureType = TemperatureType.CELSIUS
                R.id.radioButtonFahrenheit -> temperatureType = TemperatureType.FAHRENHEIT
                R.id.radioButtonKelvin -> temperatureType = TemperatureType.KELVIN
                else -> {}
            }
        }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}