package com.zemoga.zemogatest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zemoga.zemogatest.api.ApiService
import com.zemoga.zemogatest.api.RetrofitFactory
import com.zemoga.zemogatest.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class PostsViewModel : ViewModel() {

    private var posts = MutableLiveData<List<Post>>()
    private val selected = MutableLiveData<Post>()

    lateinit var apiService: ApiService


    fun getPosts(): LiveData<List<Post>> = posts

    fun getSelectedPost(): LiveData<Post> = selected


    fun setSelectedPost(product: Post) {
        selected.value = product
    }

    fun getPostsList(): List<Post>? {
        return posts.value
    }

    fun loadPosts() {

        apiService = RetrofitFactory.retrofit().create(ApiService::class.java)

        apiService.requestPosts().enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Timber.d(response.body()?.get(0).toString())

                if (response.isSuccessful) {
                    posts.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Timber.d("error ${t.message}")
            }
        })
    }
}
