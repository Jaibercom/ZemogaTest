package com.zemoga.zemogatest.api

import com.zemoga.zemogatest.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun requestPosts(): Call<List<Post>>

}
