package com.zemoga.zemogatest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zemoga.zemogatest.api.ApiService
import com.zemoga.zemogatest.api.RetrofitFactory
import com.zemoga.zemogatest.model.Comment
import com.zemoga.zemogatest.model.Post
import com.zemoga.zemogatest.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class DetailViewModel : ViewModel() {

    private var post = MutableLiveData<Post>()

    val observablePost: LiveData<Post>
        get() = post

    private var user = MutableLiveData<User>()

    val observableUser: LiveData<User>
        get() = user

    private var comments = MutableLiveData<List<Comment>>()

    val observableComment: LiveData<List<Comment>>
        get() = comments


    //TODO create a repository
    fun requestUser(userId: Int) {
        Timber.d("Loading user")
        val apiService = RetrofitFactory.apiService()
        apiService.requestUser(userId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Timber.d("onResponse")

                if (response.isSuccessful) {
                    user.value = response.body()
                } else {
                    //TODO show error message
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun requestComments(postId: Int) {
        Timber.d("Loading Comments")

        val apiService = RetrofitFactory.apiService()
        apiService.requestComments(postId).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                Timber.d("onResponse")

                if (response.isSuccessful) {
                    comments.value = response.body()
                } else {
                    //TODO show error message
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
            }
        })
    }
}
