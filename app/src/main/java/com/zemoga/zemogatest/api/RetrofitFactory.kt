package com.zemoga.zemogatest.api

import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    val baseUrl = "https://jsonplaceholder.typicode.com"

    val gson = GsonBuilder()
        .setLenient()
        .create()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
//        .callbackExecutor(Executors.newSingleThreadExecutor())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
