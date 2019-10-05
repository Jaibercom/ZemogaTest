package com.zemoga.zemogatest.api

import com.zemoga.zemogatest.model.Post
import com.zemoga.zemogatest.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("posts")
    fun requestPosts(): Call<List<Post>>

    @GET("/users/{id}")
    fun requestUser(@Path("id") id: Int): Callback<User>

    @GET("/users")
    fun requestUser(@Query("id") id: Int, cb: Callback<User>)

    @GET("/users/{id}")
    fun getUser(@Path(value = "id") userId: Int): Call<User>

}
