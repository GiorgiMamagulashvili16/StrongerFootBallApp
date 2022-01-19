package com.example.strongerfootballapp.utils

sealed class Response<out T>{

    data class Success<T>(val data: T, val message: String? = null)
        : Response<T>()

    data class Error<T>(val message: String?): Response<T>()

}