package com.example.healthcare.common

import java.text.SimpleDateFormat

object DateUtil {
    fun dateTimeToMil(date: String, time: String) : Long?{
        val dateObj = SimpleDateFormat.getDateTimeInstance().parse("$date $time")
        if (dateObj != null) {
            return dateObj.time
        }
        return  null
    }
}