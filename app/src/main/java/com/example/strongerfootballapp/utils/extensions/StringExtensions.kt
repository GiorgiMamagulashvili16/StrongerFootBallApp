package com.example.strongerfootballapp.utils.extensions

fun String.shortenLastName(): String{
    val spaceIndex = this.indexOf(" ")
    return this.dropLast(length - spaceIndex - 2).plus(".")
}