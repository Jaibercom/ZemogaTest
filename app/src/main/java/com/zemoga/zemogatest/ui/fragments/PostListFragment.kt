package com.zemoga.zemogatest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.ui.activities.PostActivity
import com.zemoga.zemogatest.ui.dummy.PostRecyclerViewAdapter
import com.zemoga.zemogatest.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.post_list.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class PostListFragment : Fragment() {

    private lateinit var adapter: PostRecyclerViewAdapter
    private lateinit var postViewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)
        postViewModel.loadPosts()
        suscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(post_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter = PostRecyclerViewAdapter(activity as PostActivity, false, postViewModel)
        recyclerView.adapter = adapter
    }

    private fun suscribe() {
        postViewModel.getPosts().observe(this, Observer { posts ->
            Timber.d("Size: ${posts?.size}")
//                adapter.updateList(productList)
            for (value in posts) {
                Timber.d("Title: ${value.title}")
            }

            adapter.updatePostList(posts)
        })
    }

    companion object {

        private val fragment = PostListFragment()

        fun newInstance(): PostListFragment {
            return fragment
        }
    }
}
