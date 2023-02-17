package com.example.thermometermodule.src

class Temperature(_celsius: Double) {
    private var endStr = ""
    private var celsius = _celsius
    private var fahrenheit: Double
        get() = celsius * 9.0 / 5.0 + 32.0
        set(value) {
            celsius = (value - 32.0) * 5.0 / 9.0
        }
    private var kelvin: Double
        get() = celsius + 273.15
        set(value) {
            celsius = value - 273.15
        }

    fun calculateTemp(season : Season, temperatureType: TemperatureType, humidity: Double): String {
        endStr = when (temperatureType) {
            TemperatureType.CELSIUS -> "The temperature is $celsius ˚C\n"
            TemperatureType.FAHRENHEIT -> "The temperature is $fahrenheit ˚F\n"
            TemperatureType.KELVIN -> "The temperature is $kelvin K\n"
        }

        val temp = season.value[0] as ClosedFloatingPointRange<Double>

        if (celsius in temp){ //celsius !in season) {
            endStr +="The comfortable temperature is from ${temp.start} to ${temp.endInclusive} ˚C.\n"

            if (celsius < temp.start) endStr += "Please, make it warmer by ${temp.start - celsius} degrees.\n"
            else endStr += "Please, make it colder by ${celsius - temp.endInclusive} degrees."
        }
        val seasonHumidity = season.value[1] as ClosedFloatingPointRange<Double>
        endStr +="\nThe comfortable humidity is from ${seasonHumidity.start}% to ${seasonHumidity.endInclusive}%.\n"
        if (humidity !in seasonHumidity) {
            endStr +="The humidity not comfortable.\n"
            if (humidity < seasonHumidity.start)  endStr +="Advice to increase it by ${seasonHumidity.start - humidity} number of units.\n"
            else  endStr +="Advice to decrease it by ${humidity - seasonHumidity.endInclusive} number of units.\n"
        } else {
            endStr +="The humidity is comfortable."
        }
        return endStr
    }
}
