package com.aengussong.movieviewie.util

import java.text.SimpleDateFormat
import java.util.*

class Utils{
    companion object{
        private val df = SimpleDateFormat(UI_DATE_FORMAT, Locale.ENGLISH)

        fun convertTime(time:Long):String{
            val date = Date(time)
            return df.format(date)
        }
    }
}