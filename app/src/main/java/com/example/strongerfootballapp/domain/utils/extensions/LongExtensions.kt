package com.example.strongerfootballapp.domain.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toFormattedDate(): String{
    val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    val date = Date(this)
    return formatter.format(date)
}

private const val DATE_FORMAT = "dd MMMM yyyy"