package com.zemoga.zemogatest

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zemoga.zemogatest.ZemogaApplication.Companion.database
import com.zemoga.zemogatest.api.RetrofitFactory
import com.zemoga.zemogatest.db.entity.PostEntity
import com.zemoga.zemogatest.model.Post

//class DataRepository(private val postDao: PostDao) {
class DataRepository {

    private var apiService = RetrofitFactory.apiService()
    private val postDao = database?.postDao()

    suspend fun getPosts(): List<Post>? {
        val dataLiveData = postDao?.getAll() as MutableList<PostEntity>
        var postsLiveData = mutableListOf<Post>()

//        postsLiveData = Transformations.map(dataLiveData, {
//            return it.
//
//        })


        dataLiveData?.let {
            dataLiveData.forEach {
                postsLiveData.add(
                    Post(
                        userId = 1,
                        id = it.id,
                        body = it.body,
                        isFavorite = false,
                        isRead = false,
                        title = it.title
                    )
                )
            }
        }

        if (postsLiveData.isNullOrEmpty()) {
            postsLiveData = requestPosts() as MutableList<Post>

            postsLiveData?.let {
                postsLiveData.forEach {
                    dataLiveData.add(
                        PostEntity(
//                            userId = 1,
                            id = it.id,
                            body = it.body,
//                            isFavorite = false,
//                            isRead = false,
                            title = it.title
                        )
                    )
                }
            }


            postDao.insertAll(dataLiveData)
        }
        return postsLiveData
    }


    suspend fun loadPostsDB(): List<PostEntity>? = postDao?.getAll()

    suspend fun requestPosts(): List<Post> = apiService.requestPosts()
    suspend fun requestUser(userId: Int) = apiService.requestUser(userId)
    suspend fun requestComments(postId: Int) = apiService.requestComments(postId)


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DataRepository().also { instance = it }
            }
    }

}


