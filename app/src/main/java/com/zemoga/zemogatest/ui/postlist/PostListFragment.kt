package com.zemoga.zemogatest.ui.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.ui.PostViewModel
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.fragment_post_list.coordinator
import kotlinx.android.synthetic.main.fragment_post_list.delete_fab
import kotlinx.android.synthetic.main.post_list.post_list
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class PostListFragment : Fragment(), PostAdapter.OnItemClickListener {

    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)

        if (postViewModel.observablePostList.value.isNullOrEmpty()) {
            postViewModel.requestPosts()
        }
        subscribeUi()
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

        postViewModel.observablePostList.value?.let {
            postAdapter.updatePostList(it)
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        postAdapter = PostAdapter(this as PostAdapter.OnItemClickListener)
        recyclerView.adapter = postAdapter

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(postAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun subscribeUi() {
        postViewModel.observablePostList.observe(this, Observer { posts ->
            Timber.d("Size: ${posts?.size}")
            postAdapter.updatePostList(posts)
        })
    }

    override fun onItemClick(position: Int) {
        Timber.d("onPostClicked $position")

        this.postViewModel.setPosition(position)
        this.postViewModel.observablePostList.value?.get(position)?.isRead = true

        view?.let {
            findNavController(it).navigate(R.id.action_postListFragment_to_postDetailFragment)
        }
    }

    private fun onDeleteClick() = View.OnClickListener { view ->
        //        nav_view.visibility = View.GONE
//        Snackbar.make(view, "Delete?", Snackbar.LENGTH_LONG)
//            .setAction("OK", oKListener()).show()

        Snackbar.make(coordinator, "Your message", Snackbar.LENGTH_LONG).apply {
            view.layoutParams = (view.layoutParams as CoordinatorLayout.LayoutParams).apply {
                setMargins(leftMargin, topMargin, rightMargin, nav_view.height)
            }
        }.show()

    }

    private fun oKListener() = View.OnClickListener {
        Timber.d("Deleting Post")
        postViewModel.setPostList(emptyList())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_post_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                postViewModel.requestPosts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
