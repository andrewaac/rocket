package com.andrewaac.rocket.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("launch_information")
fun TextView.bindLaunchInformation(inputString: String?) {
    text = inputString ?: "N/A"
}

@BindingAdapter("launch_information")
fun TextView.bindLaunchSuccess(successful: Boolean) {
    text = if (successful) "Successful" else "Failure"
}