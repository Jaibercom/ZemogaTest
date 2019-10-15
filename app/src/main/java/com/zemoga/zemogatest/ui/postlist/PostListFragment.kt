package com.zemoga.zemogatest.ui.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.ui.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_list.delete_fab
import kotlinx.android.synthetic.main.post_list.post_list
import timber.log.Timber


/**
 * A Post list Fragment.
 */
class PostListFragment : Fragment(), PostAdapter.OnItemClickListener {

    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)

        showPosts()
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
        delete_fab.setOnClickListener(onDeleteClick())
    }

     override fun onResume() {
        super.onResume()

//        postViewModel.getPosts().value?.let {
//            postAdapter.updatePostList(it)
//        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        postAdapter = PostAdapter(this as PostAdapter.OnItemClickListener)
        recyclerView.adapter = postAdapter

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(postAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun showPosts() {
        Timber.d("showPosts")

        postViewModel.getPosts()?.observe(this, Observer { posts ->
            Timber.d("Size: ${posts?.size}")
            postAdapter.updatePostList(posts)
        })
    }

    override fun onItemClick(position: Int) {
        Timber.d("onPostClicked $position")

        this.postViewModel.setPosition(position)
        this.postViewModel.select(position)

        view?.let {
            findNavController(it).navigate(R.id.action_postListFragment_to_postDetailFragment)
        }
    }

    private fun onDeleteClick() = View.OnClickListener { view ->
        Snackbar.make(view, "Delete?", Snackbar.LENGTH_LONG)
            .setAction("OK", oKListener()).show()
    }

    private fun oKListener() = View.OnClickListener {
        Timber.d("Deleting Post")
        postViewModel.deletePosts()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_post_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                Timber.d("Action refresh")
                showPosts()
//                postViewModel.getPosts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
