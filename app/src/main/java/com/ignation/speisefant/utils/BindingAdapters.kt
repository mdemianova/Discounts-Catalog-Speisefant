package com.ignation.speisefant.utils

import android.content.res.Resources
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.ignation.speisefant.R
import com.ignation.speisefant.domain.Product
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Adapter for displaying Product image.
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * Adapter for formatting Product discount period.
 */
@BindingAdapter("discountPeriod")
fun setDiscountPeriod(textView: TextView, product: Product) {
    textView.text = convertDatesToString(product.startDate, product.endDate, textView.resources)
}

private fun convertDatesToString(startDate: Long, endDate: Long, res: Resources): String {
    val start = convertToStringFromLong(startDate)
    val end = convertToStringFromLong(endDate)

    return res.getString(R.string.date, start, end)
}

private fun convertToStringFromLong(date: Long): String {
    val formatter = DateTimeFormatter.ofPattern("d MMM")
    val localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.of("Europe/Paris"))

    return formatter.format(localDate)
}

/**
 * Adapter for formatting product prices.
 */
@BindingAdapter("formattedPrice")
fun setFormattedPrice(textView: TextView, price: Int) {
    textView.text = convertIntToFloat(price, textView.resources)
}

private fun convertIntToFloat(price: Int, res: Resources): String {
    return res.getString(R.string.price, price.toFloat() / 100)
}