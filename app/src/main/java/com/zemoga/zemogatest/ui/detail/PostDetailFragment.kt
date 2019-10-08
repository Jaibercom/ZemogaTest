package com.zemoga.zemogatest.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.model.Comment
import com.zemoga.zemogatest.model.Post
import com.zemoga.zemogatest.model.User
import com.zemoga.zemogatest.ui.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_detail.*
import timber.log.Timber

/**
 * A fragment representing a single Post detail screen.
 *
 */
class PostDetailFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var detailViewModel: DetailViewModel

    private lateinit var post: Post
    private var user: User? = null
    private var commentList: List<Comment>? = null

    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        subscribeUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView(commentRecyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        commentAdapter = CommentAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = commentAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun subscribeUI() {
        postViewModel.observablePosition.observe(this, Observer { position ->
            // update UI
            position?.let {
                Timber.i("Position $position")
                post = postViewModel.observablePostList.value?.get(it)!!

                updateUi()
                requestUser(post.userId)
                requestComments(post.id)
            }
        })

        detailViewModel.observableUser.observe(this, Observer {
            user = it
            updateUi()
        })

        detailViewModel.observableComment.observe(this, Observer {
            Timber.d("ListComment size: ${it.size}")
            for (value in it) {
                Timber.d("Comment: ${value.name}")
            }
            commentList = it
            updateUi()
        })
    }

    private fun updateUi() {
        post_detail.text = post.title

        user?.let {
            name.text = it.name
            email.text = it.email
            phone.text = it.phone
            website.text = it.website
        }

        commentList?.let {
            commentAdapter.updateCommentList(it)
        }
    }

    private fun requestUser(userId: Int) {
        detailViewModel.requestUser(userId)
    }

    private fun requestComments(postId: Int) {
        detailViewModel.requestComments(postId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        Timber.d("onOptionsItemSelected")
        return when (item.itemId) {
            R.id.action_favorite -> {
                Timber.d("addFavorite")
                postViewModel.addFavorites()
                this.findNavController()
                    .navigate(R.id.action_postDetailFragment_to_postListFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
