package com.ignation.speisefant.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.ignation.speisefant.R
import com.ignation.speisefant.domain.Product

/**
 * Adapter for displaying Product image
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
 * Adapter for formatting Product discount period
 */
@BindingAdapter("discountPeriod")
fun setDiscountPeriod(textView: TextView, product: Product) {
    textView.text = convertDatesToString(product.startDate, product.endDate, textView.resources)
}

/**
 * Adapter for formatting product prices
 */
@BindingAdapter("formattedPrice")
fun setFormattedPrice(textView: TextView, price: Int) {
    textView.text = convertIntToFloat(price, textView.resources)
}