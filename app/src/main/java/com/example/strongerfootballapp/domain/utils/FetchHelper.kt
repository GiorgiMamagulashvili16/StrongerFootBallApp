package com.example.strongerfootballapp.domain.utils

import retrofit2.Response


inline fun <D, T> fetchData(
    response: () -> Response<D>,
    call: (body: D) -> Resource<T>
): Resource<T> {
    return try {
        val resp = response.invoke()
        if (resp.isSuccessful) {
            resp.body()?.let {
                call.invoke(it)
            } ?: Resource.Error(DATA_IS_NULL_MESSAGE)
        } else {
            Resource.Error("ge")
        }
    } catch (e: Exception) {
        Resource.Error(e.message)
    }
}

const val DATA_IS_NULL_MESSAGE = "data is null"