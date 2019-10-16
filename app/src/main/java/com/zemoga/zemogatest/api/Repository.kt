package com.zemoga.zemogatest.api

class Repository {

    private var apiService = RetrofitFactory.apiService()

    suspend fun requestPosts() = apiService.requestPosts()
    suspend fun requestUser(userId: Int) = apiService.requestUser(userId)
    suspend fun requestComments(postId: Int) = apiService.requestComments(postId)

}
