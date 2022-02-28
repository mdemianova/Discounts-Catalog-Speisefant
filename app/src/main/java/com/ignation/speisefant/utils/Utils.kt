package com.ignation.speisefant.utils

import java.time.LocalDate
import java.time.ZoneId

/**
 * Creates a display period for discount. Used for selecting products from database.
 * Gets date range from the start of Sunday to the start of next Sunday, based on current date.
 */
fun createActualPeriod(): Pair<Long, Long> {
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
    val lastDayOffset = (6 - currentDayOfWeek).toLong()
    val lastDay = today.plusDays(lastDayOffset).atStartOfDay(ZoneId.of("Europe/Paris"))

    return lastDay.toInstant().toEpochMilli()
}




