package com.asad.xischetest

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

object Utils {
    fun getWeekdayFromDate(dateString: String): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val date = sdf.parse(dateString)
            val calendar = Calendar.getInstance()
            calendar.time = date
            val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            dayFormat.format(date) // Full name of the day
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getWeekdayFromDateNew(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, formatter)
        return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }
}