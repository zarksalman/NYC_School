package com.nyc.highschool.utils

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nyc.highschool.R
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
  Picasso.get().load("http://image.tmdb.org/t/p/w500$url")
    .placeholder(R.drawable.user_placeholder)
    .error(R.drawable.user_placeholder_error)
    .into(view);
}

@BindingAdapter("text")
fun changeTextColor(view: TextView, data: String) {


  val separated = data.split("-".toRegex()).toTypedArray()
  if ((Calendar.getInstance().get(Calendar.YEAR)).toString() == separated[0]) {
    val wordtoSpan: Spannable = SpannableString(data)
    wordtoSpan.setSpan(ForegroundColorSpan(Color.RED), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    wordtoSpan.setSpan(StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    view.text = wordtoSpan

  } else
    view.text = data


}

fun doubleToStringNoDecimal(d: Int): String? {
  val formatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
  formatter.applyPattern("#,###")
  return formatter.format(d)
}