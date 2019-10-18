package com.zemoga.zemogatest.ui.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.zemogatest.MainActivity
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.ui.PostViewModel
import com.zemoga.zemogatest.ui.postlist.PostAdapter
import kotlinx.android.synthetic.main.fragment_favorites.favorite_list
import timber.log.Timber

class FavoritesFragment : Fragment(), PostAdapter.OnItemClickListener {

    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)

        showPosts()
    }

    override fun onResume() {
        super.onResume()

        activity?.let{
            (activity as MainActivity).showNavBottom(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(favorite_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        postAdapter = PostAdapter(this as PostAdapter.OnItemClickListener)
        recyclerView.adapter = postAdapter
    }

    private fun showPosts() {
        Timber.d("showPosts")

        postViewModel.getFavoritePosts()?.observe(this, Observer { favorites ->
            Timber.d("Size: ${favorites?.size}")
            //var favorites  =

            postAdapter.updatePostList(favorites)
        })
    }

    override fun onItemClick(position: Int) {
        Timber.d("onPostClicked $position")

        this.postViewModel.setPosition(position)
        this.postViewModel.select(position)

        view?.let {
            Navigation.findNavController(it).navigate(R.id.action_navigation_favorites_to_navigation_detail)
        }
    }
}
