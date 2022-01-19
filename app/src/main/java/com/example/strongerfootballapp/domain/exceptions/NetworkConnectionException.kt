package com.example.strongerfootballapp.domain.exceptions

import java.io.IOException


class NetworkConnectionException : IOException() {
    override val message: String
        get() = NO_INTERNET_MESSAGE

    companion object {
        private const val NO_INTERNET_MESSAGE = "no internet connection"
    }
}