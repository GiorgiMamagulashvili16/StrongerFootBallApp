package com.example.strongerfootballapp.network

import com.example.strongerfootballapp.data.api.ApiService
import com.example.strongerfootballapp.domain.interceptors.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

fun provideHttpClient(
    networkConnectionInterceptor: NetworkConnectionInterceptor,
): OkHttpClient =
    OkHttpClient().newBuilder().addInterceptor(networkConnectionInterceptor)
        .build()

fun provideMatchApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
private const val BASE_URL = "https://www.mocky.io/"