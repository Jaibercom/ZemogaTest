package com.zemoga.zemogatest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zemoga.zemogatest.DataRepository
import com.zemoga.zemogatest.model.Post
import kotlinx.coroutines.Dispatchers
import timber.log.Timber


/**
 * Post View Model
 */
class PostViewModel : ViewModel() {

    private var repository = DataRepository.getInstance()
    private var posts: MutableLiveData<List<Post>>? = null
    private val selected = MutableLiveData<Post>()
    private val position = MutableLiveData<Int>()


    fun getPosts(): LiveData<List<Post>> {
        if (posts?.value.isNullOrEmpty()) {
            loadPosts()
        }
        return posts as LiveData<List<Post>>
    }

    fun getFavoritePosts(): LiveData<List<Post>>? {

        val liveData = MutableLiveData<List<Post>>()
        posts?.value?.let { posts ->
            liveData.value = posts.filter {
                it.isFavorite
            }
        }

        return liveData
    }

    fun deletePosts() {
        posts?.value = emptyList()
    }

    private fun loadPosts() {
        posts = liveData(Dispatchers.IO) {
            val data = repository.getPosts()

            emit(data)
        } as MutableLiveData<List<Post>>

//        posts = liveData(Dispatchers.IO) {
//            val data = repository.requestPosts()
//            emit(data)
//        } as MutableLiveData<List<Post>>
    }

    fun select(pos: Int) {
        posts?.value?.get(pos)?.let {
            selected.value = it
            it.isRead = true
        }
    }

    fun getSelected(): LiveData<Post> {
        return selected
    }

    fun setPosition(pos: Int) {
        position.value = pos
    }

    fun addToFavorites() {
        posts?.value?.let {
            val favorite = it[position.value!!].isFavorite
            it[position.value!!].isFavorite = !favorite
        }
        val list = posts?.value?.filter { it.isFavorite }

        Timber.d("Favorites: ${list?.size}")
    }
}
