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

    fun setPosition(position: Int) {
        this.position.value = position
    }

    //TODO crear repository para buscar en local o hacer llamado
    fun requestPosts() {
        Timber.d("Loading postList")
        val apiService = RetrofitFactory.retrofit().create(ApiService::class.java)

        apiService.requestPosts().enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Timber.d("onResponse")

                if (response.isSuccessful) {
                    postList.value = response.body()
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
