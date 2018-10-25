package com.arifudesu.kadeproject2.util

import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

object DateConverter {

    private fun formatDate(date: String, format: String): String {
        var result = ""

        val old = SimpleDateFormat("yyyy-MM-dd")
        try {
            val oldDate = old.parse(date)
            val newFormat = SimpleDateFormat(format)
            result = newFormat.format(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }

    fun getLongDate(date: String?): String {
        return formatDate(date!!, "EEEE, MMM d, yyyy")
    }
}
