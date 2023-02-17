package com.example.thermometermodule.src

enum class Season(var value: List<Any?>) {
    WINTER(ArrWin),
    SUMMER(ArrSum),
}

private var ArrWin = listOf(20.0..22.0, 30.0..60.0)
private var ArrSum = listOf(22.0..25.0, 30.0..45.0)