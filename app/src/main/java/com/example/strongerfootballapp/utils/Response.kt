package com.example.strongerfootballapp.utils

data class Response<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
) {

    enum class Status {
        Success,
        Error,
    }

    companion object {

        fun <T> success(data: T): Response<T> {
            return Response(Status.Success, data, null)
        }

        fun <T> error(message: String?): Response<T> {
            return Response(Status.Error, null, message)
        }


    }

}