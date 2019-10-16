package com.zemoga.zemogatest.api

import com.zemoga.zemogatest.model.Comment
import com.zemoga.zemogatest.model.Post
import com.zemoga.zemogatest.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("posts")
    suspend fun requestPosts(): List<Post>

    @GET("/users/{id}")
    suspend fun requestUser(@Path(value = "id") userId: Int): User

    @GET("/comments")
    suspend fun requestComments(@Query(value = "postId") userId: Int): List<Comment>
}
