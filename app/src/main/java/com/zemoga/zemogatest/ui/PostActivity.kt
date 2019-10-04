package com.zemoga.zemogatest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.ui.dummy.PostRecyclerViewAdapter
import com.zemoga.zemogatest.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.activity_post_list.*
import kotlinx.android.synthetic.main.post_list.*
import timber.log.Timber

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PostDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PostListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private lateinit var adapter: PostRecyclerViewAdapter

    private lateinit var postsViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (post_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }


        if (savedInstanceState == null) {
            // Add fragment to Activity
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, PostListFragment.newInstance())
                .addToBackStack("PostsListFragment")
                .commit()
        }


//        postsViewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
//        postsViewModel.loadPosts()
//        suscribe()
//
//        setupRecyclerView(post_list)
    }

//    private fun setupRecyclerView(recyclerView: RecyclerView) {
//        adapter = PostRecyclerViewAdapter(this, twoPane)
//        recyclerView.adapter = adapter
//    }
//
//    private fun suscribe() {
//        postsViewModel.getPosts().observe(this, Observer { posts ->
//            Timber.d("Size: ${posts?.size}")
////                adapter.updateList(productList)
//            for (value in posts) {
//                Timber.d("Title: ${value.title}")
//            }
//
//            adapter.updatePostList(posts)
//        })
//    }
}
