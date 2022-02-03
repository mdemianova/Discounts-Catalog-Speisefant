package com.ignation.speisefant.utils

import android.content.res.Resources
import com.ignation.speisefant.R
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertIntToFloat(price: Int, res: Resources): String {
    return res.getString(R.string.price, price.toFloat() / 100)
}

/**
 * Get date range from start of Sunday to start of Sunday as a display period.
 */
fun createActualPeriod(): Pair<Long, Long> {
    // Start - Sunday, End - Sunday
    val currentDate = LocalDate.now(ZoneId.of("Europe/Paris"))
    val startDay = getFirstDay(currentDate)
    val endDay = getLastDay(currentDate)

    return Pair(startDay, endDay)
}

private fun getFirstDay(today: LocalDate): Long {
    val currentDayOfWeek = today.dayOfWeek.value
    val firstDayOffset = -currentDayOfWeek.toLong()
    val firstDay = today.plusDays(firstDayOffset).atStartOfDay(ZoneId.of("Europe/Paris"))

    return firstDay.toInstant().toEpochMilli()
}

private fun getLastDay(today: LocalDate): Long {
    val currentDayOfWeek = today.dayOfWeek.value
    val lastDayOffset = (7 - currentDayOfWeek).toLong()
    val lastDay = today.plusDays(lastDayOffset).atStartOfDay(ZoneId.of("Europe/Paris"))

    return lastDay.toInstant().toEpochMilli()
}

fun convertDatesToString(startDate: Long, endDate: Long, res: Resources): String {
    val start = convertToStringFromLong(startDate)
    val end = convertToStringFromLong(endDate)

    return res.getString(R.string.date, start, end)
}

private fun convertToStringFromLong(date: Long): String {
    val formatter = DateTimeFormatter.ofPattern("d MMM")
    val localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.of("Europe/Paris"))

    return formatter.format(localDate)
}

