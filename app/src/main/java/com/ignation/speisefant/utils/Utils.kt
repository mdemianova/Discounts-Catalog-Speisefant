package com.ignation.speisefant.utils

import android.content.res.Resources
import com.ignation.speisefant.R

fun convertDatesToString(startDate: String, endDate: String, res: Resources): String {
    return res.getString(R.string.date, startDate, endDate)
}

fun convertIntToFloat(price: Int, res: Resources): String {
    return res.getString(R.string.price, price.toFloat() / 100)
}