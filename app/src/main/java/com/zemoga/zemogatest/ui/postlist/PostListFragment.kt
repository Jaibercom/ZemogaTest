package com.zemoga.zemogatest.ui.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.ui.PostViewModel
import kotlinx.android.synthetic.main.post_list.*
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class PostListFragment : Fragment(), PostAdapter.OnItemClickListener {

    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter

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

    override fun onResume() {
        super.onResume()

        postViewModel.observablePostList.value?.let {
            postAdapter.updatePostList(it)
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        postAdapter =
            PostAdapter(this as PostAdapter.OnItemClickListener)
        recyclerView.adapter = postAdapter
    }

    private fun suscribe() {
        postViewModel.observablePostList.observe(this, Observer { posts ->
            Timber.d("Size: ${posts?.size}")
            postAdapter.updatePostList(posts)
        })
    }

    override fun onItemClick(position: Int) {
        Timber.d("onPostClicked ${position}")

        this.postViewModel.setPosition(position)

        view?.let {
            findNavController(it).navigate(R.id.action_postListFragment_to_postDetailFragment)
        }
    }

    companion object {
        private val fragment = PostListFragment()

        fun newInstance(): PostListFragment {
            return fragment
        }
    }
}
