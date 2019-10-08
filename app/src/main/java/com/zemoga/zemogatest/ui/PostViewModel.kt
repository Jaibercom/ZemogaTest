package com.zemoga.zemogatest.ui

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

    private var postList = MutableLiveData<List<Post>>()
    private val position = MutableLiveData<Int>()

    val observablePostList: LiveData<List<Post>>
        get() = postList

    val observablePosition: LiveData<Int>
        get() = position

    fun setPosition(pos: Int) {
        position.value = pos
    }

    fun setPostList(posts: List<Post>?) {
        postList.value = posts
    }

    fun addFavorites() {
        postList.value?.let {
            val favorite = it[position.value!!].isFavorite
            it[position.value!!].isFavorite = !favorite
        }
        val list = postList.value?.filter { it.isFavorite }

        Timber.d("Favorites: ${list?.size}")
    }

    //TODO create a repository
    fun requestPosts() {
        Timber.d("Loading postList")

        val apiService = RetrofitFactory.apiService()
        apiService.requestPosts().enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Timber.d("onResponse")

                if (response.isSuccessful) {
                    setPostList(response.body())
                } else {
                    //TODO Show error
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Timber.d("error ${t.message}")
            }
        })
    }
}
