package com.zemoga.zemogatest.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.model.Post
import com.zemoga.zemogatest.ui.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_detail.*
import timber.log.Timber

/**
 * A fragment representing a single Post detail screen.
 *
 */
class PostDetailFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var post: Post

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)
        // Show the Up button in the action bar.
        postViewModel.getPosition().observe(this, Observer { position ->
            // update UI
            position?.let {
                Timber.i("Position $position")
                post = postViewModel.observablePostList.value?.get(it)!!

                updateUi()
            }
        })
    }

    private fun updateUi() {
        Timber.i("Text: ${post.title}")
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
