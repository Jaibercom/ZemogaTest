package com.zemoga.zemogatest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.viewmodel.PostsViewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var postsViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postsViewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)

        Timber.i("Loading post")
        postsViewModel.loadPosts()
        suscribe()
    }

    private fun suscribe() {
        postsViewModel.getPosts().observe(this, Observer { posts ->
            Timber.d("Size: ${posts?.size}")
//                adapter.updateList(productList)
            for (value in posts) {
                Timber.d("Title: ${value.title}")
            }
        })
    }
}
