package com.zemoga.zemogatest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.model.Post
import com.zemoga.zemogatest.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.post_detail.*
import timber.log.Timber

/**
 * A fragment representing a single Pot detail screen.
 * This fragment is either contained in a [PostActivity]
 * in two-pane mode (on tablets) or a [PostDetailActivity]
 * on handsets.
 */
class PostDetailFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fargment_post_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show the Up button in the action bar.
        postViewModel.getPosition().observe(this, Observer { item ->
            // update UI
            item?.let {
                post = postViewModel.getPosts().value?.get(it)!!

                Timber.i("Product ${post.title}")
                updateUi()
            }
        })
    }

    fun updateUi() {
        post_detail.text = post.title
    }

    companion object {

        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_POS = "item_position"

        fun newInstance(position: Int?): PostDetailFragment {

            return PostDetailFragment().apply {
                arguments = Bundle().apply {
                    position?.let { putInt(ARG_ITEM_POS, it) }
                }
            }
        }
    }
}
