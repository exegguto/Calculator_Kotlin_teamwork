package com.example.circlesmodule.src

import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

object CirclesModuleMath {
     fun circles(
        x1: Double,
        y1: Double,
        r1: Double,
        x2: Double,
        y2: Double,
        r2: Double
    ): String {
        val EPS = 1e-6
        val resultString: StringBuilder = StringBuilder()

        if (r1 <= 0 || r2 <= 0) resultString.append("radius cannot be <= 0")

        val d: Double = sqrt(abs(x1 - x2).pow(2) + abs(y1 - y2).pow(2))

        if (d > r1 + r2) {
            resultString.append("the circles not intersect")
        } else if (abs(d - (r1 + r2)) < EPS) {
            resultString.append("the circles touch")
            calcCoord(r1, r2, d, x1, y1, x2, y2, false, resultString)
        } else if (d < EPS) {
            if (abs(r1 - r2) > EPS) resultString.append("the circles not intersect")
            else resultString.append("the circles intersect in infinity points")
        } else if (d < abs(r1 - r2)) {
            resultString.append("the circles not intersect and O1 is in O2")
        } else if (abs(d - abs(r1 - r2)) < EPS) {
            resultString.append("the circles touch and O1 is in O2")
            calcCoord(r1, r2, d, x1, y1, x2, y2, false, resultString)
        } else if (d > EPS && min(r1, r2) >= d) {
            resultString.append("the circles intersect and O1 is in O2")
            calcCoord(r1, r2, d, x1, y1, x2, y2, true, resultString)
        } else if (d > EPS && abs(r1 + r2) > d) {
            resultString.append("the circles intersect")
            calcCoord(r1, r2, d, x1, y1, x2, y2, true, resultString)
        }

        return resultString.toString()
    }

    private fun calcCoord(
        r1: Double,
        r2: Double,
        d: Double,
        x1: Double,
        y1: Double,
        x2: Double,
        y2: Double,
        isTwoPoints: Boolean,
        str: StringBuilder
    ) {
        val a: Double = (r1.pow(2) - r2.pow(2) + d.pow(2)) / (2 * d)
        val h = sqrt(r1.pow(2) - a.pow(2))

        val x0: Double = x1 + a * (x2 - x1) / d
        val y0: Double = y1 + a * (y2 - y1) / d

        val firstX: Double = x0 + h * (y2 - y1) / d
        val firstY: Double = y0 - h * (x2 - x1) / d

        val secondX: Double = x0 - h * (y2 - y1) / d
        val secondY: Double = y0 + h * (x2 - x1) / d

        if (isTwoPoints) {
            str.append("\nx1: $firstX;\ny1: $firstY;\nx2: $secondX;\ny2: $secondY")
        } else {
            str.append("\nx1: $firstX;\ny1: $firstY;")
        }
    }
}