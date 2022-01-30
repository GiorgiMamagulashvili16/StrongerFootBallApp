package com.example.match_action_views.extensions

fun String.shortenLastName(): String{
    val spaceIndex = this.indexOf(" ")
    return this.dropLast(length - spaceIndex - 2).plus(".")
}