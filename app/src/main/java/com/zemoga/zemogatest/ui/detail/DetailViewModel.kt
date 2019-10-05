package com.zemoga.zemogatest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zemoga.zemogatest.api.RetrofitFactory
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

    fun getNote(id: Int) {
//        post.value = NotesManager.getNote(id)
    }

//    fun getUser(id: Int) {
//        requestUser(id)
//    }

    //TODO crear repository para buscar en local o hacer llamado
    fun requestUser(id: Int) {
        Timber.d("Loading user")

        val apiService = RetrofitFactory.apiService()

        apiService.getUser(id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Timber.d("onResponse")

                if (response.isSuccessful) {
                    user.value = response.body()
                } else {
                    //TODO mostrar error
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

}
