package com.zemoga.zemogatest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zemoga.zemogatest.api.Repository
import com.zemoga.zemogatest.api.RetrofitFactory
import com.zemoga.zemogatest.model.Comment
import com.zemoga.zemogatest.model.Post
import com.zemoga.zemogatest.model.User
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class DetailViewModel : ViewModel() {

    private val repository = Repository()

    private var user = MutableLiveData<User>()

    private var comments = MutableLiveData<List<Comment>>()

    fun getUser(pos: Int): MutableLiveData<User> {
        user = liveData(Dispatchers.IO) {
            val data = repository.requestUser(pos)
            emit(data)
        } as MutableLiveData<User>
        return user
    }

    fun getComments(postId: Int): LiveData<List<Comment>> {
        comments = liveData(Dispatchers.IO) {
            val data = repository.requestComments(postId)
            emit(data)
        } as MutableLiveData<List<Comment>>
        return comments
    }

}
