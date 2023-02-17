package com.example.speechmodule

object SpeechModuleMath {
    private val numbersUntilTwenty = listOf(
        "zero",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen"
    )

    private val multiplesOfTen = listOf(
        "ten",
        "twenty",
        "thirty",
        "forty",
        "fifty",
        "sixty",
        "seventy",
        "eighty",
        "ninety"
    )

    private val bigNumbers = mapOf(
        "billion" to 1000_000_000,
        "million" to 1000_000,
        "thousand" to 1000,
        "hundred" to 100
    )

    fun speechModuleProcess(input: Long): String {
        var value: Long = 0
        var counter = 0

        return numberProcess(input)
    }

    private fun numberProcess(_value: Long): String {
        var value = _value
        val result: StringBuilder = StringBuilder()

        if (value != 0L) {
            if (value < 0) {
                result.append("minus ")
                value = -value
            }

            for (i in bigNumbers.keys) {
                val temp: Long = value.div(bigNumbers.getValue(i))
                value = value.rem(bigNumbers.getValue(i))

                if (temp > 0) {
                    processBigNumber(result, temp)
                    result.append("$i ")
                }
            }

            if (value > bigNumbers.getValue("hundred")) {
                processSmallNumber(result, value / bigNumbers.getValue("hundred"))
                result.append("hundred ")
                value = value.rem(bigNumbers.getValue("hundred"))
            }
            processMediumNumber(result, value)
        } else {
            result.append(numbersUntilTwenty[0])
        }

        return result.toString()
    }

    private fun processBigNumber(result: StringBuilder, _temp: Long) {
        var temp = _temp
        val hundred = temp.div(bigNumbers.getValue("hundred"))
        if (hundred > 0) {
            processSmallNumber(result, hundred)
            result.append("hundred ")
            temp = temp.rem(bigNumbers.getValue("hundred"))
        }

        processMediumNumber(result, temp)
    }

    // process numbers less than 100
    private fun processMediumNumber(result: StringBuilder, _temp: Long) {
        var temp = _temp
        if (temp >= 20) {
            result.append("${multiplesOfTen[temp.div(10).toInt() - 1]} ")
            temp = temp.rem(10)
        }

        if (temp > 0) {
            processSmallNumber(result, temp)
        }
    }

    // process numbers less than 20
    private fun processSmallNumber(result: StringBuilder, _temp: Long): StringBuilder =
        result.append("${numbersUntilTwenty[_temp.toInt()]} ")

}