package com.andrewaac.rocket.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat

class TimeUtils {

    companion object {

        private const val UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

        @SuppressLint("SimpleDateFormat")
        fun beautify(UTCString: String): String {
            val utcDateFormat = SimpleDateFormat(UTC_DATE_FORMAT)
            utcDateFormat.parse(UTCString)?.let {
                val simpleDateFormat =
                    SimpleDateFormat.getDateInstance(DateFormat.SHORT)
                return simpleDateFormat.format(it).replace('/', '-')
            } ?: kotlin.run {
                return "N/A"
            }

        }

    }

}