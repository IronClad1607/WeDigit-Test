package com.ironclad.wedigittest.utils

import kotlin.math.roundToLong

fun convertRuntime(r: Int?): String {
    val hr = r?.div(60)
    val min = r?.rem(60)
    return "${hr.toString()}hr ${min.toString()}min"
}

fun convertDate(str: String?): String {
    val month = str?.substring(5, 7)
    val year = str?.substring(0, 4)
    val date = str?.substring(8, 10)

    val monStr = when (month) {
        "01" -> "January"
        "02" -> "February"
        "03" -> "March"
        "04" -> "April"
        "05" -> "May"
        "06" -> "June"
        "07" -> "July"
        "08" -> "August"
        "09" -> "September"
        "10" -> "October"
        "11" -> "November"
        else -> "December"
    }
    return "$monStr $date $year"
}

fun convertBudget(b: Int?): String {
    val million = 1000000L
    val billion = 1000000000L
    val trillion = 1000000000000L

    val number = b!!.toDouble().roundToLong()
    if (number in million until billion) {
        val fraction = calculateFraction(number, million)
        return "$fraction Million"
    } else if (number in billion until trillion) {
        val fraction = calculateFraction(number, billion)
        return "$fraction Billion"
    }
    return number.toString()
}

private fun calculateFraction(number: Long, divisor: Long): Float {
    val truncate = (number * 10L + divisor / 2L) / divisor
    return truncate.toFloat() * 0.10f
}
