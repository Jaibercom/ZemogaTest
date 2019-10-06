package com.zemoga.zemogatest.api

import com.zemoga.zemogatest.model.Comment
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
    fun requestUser(@Path(value = "id") userId: Int): Call<User>

    @GET("/comments")
    fun requestComments(@Query(value = "postId") userId: Int): Call<List<Comment>>

    //https://jsonplaceholder.typicode.com/comments?postId=1
}
