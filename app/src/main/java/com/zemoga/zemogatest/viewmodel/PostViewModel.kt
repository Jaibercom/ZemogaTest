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

class PostViewModel : ViewModel() {

    private var posts = MutableLiveData<List<Post>>()
    private val selectedPost = MutableLiveData<Post>()

    fun getPosts(): LiveData<List<Post>> = posts

    fun getSelectedPost(): LiveData<Post> = selectedPost


    fun setSelectedPost(post: Post) {
        selectedPost.value = post
    }

//    fun getPostsList(): List<Post>? {
//        return posts.value
//    }

    //TODO crear repository para buscar en local o hacer llamado
    fun loadPosts() {
        Timber.d("Loading posts")
        val apiService = RetrofitFactory.retrofit().create(ApiService::class.java)

        apiService.requestPosts().enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
//                Timber.d(response.body()?.get(0).toString())
                Timber.d("onResponse")

                if (response.isSuccessful) {
                    posts.value = response.body()
                } else {
                    //TODO mostrar error
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Timber.d("error ${t.message}")
            }
        })
    }
}
