package com.zemoga.zemogatest.api

import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    const val BASE_URL = "https://jsonplaceholder.typicode.com"

    val gson = GsonBuilder()
        .setLenient()
        .create()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun apiService(): ApiService = retrofit().create(ApiService::class.java)
}
