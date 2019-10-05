package com.zemoga.zemogatest.ui.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.model.Post
import com.zemoga.zemogatest.ui.fragments.PostDetailFragment
import com.zemoga.zemogatest.ui.activities.PostActivity
import com.zemoga.zemogatest.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.post_list_content.view.*

/**
 *
 */
class PostRecyclerViewAdapter(
    private val parentActivity: PostActivity,
    private val twoPane: Boolean,
    private val postViewModel: PostViewModel
) :
    RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {

    private var values = mutableListOf<Post>()

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val post = v.tag as Post

            postViewModel.setSelectedPost(post)

            val fragment = PostDetailFragment.newInstance(post.userId)

            if (twoPane) {

                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.post_detail_container, fragment)
                    .commit()
            } else {

                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("PostsDetailFragment")
                    .replace(R.id.fragmentContainer, fragment)
                    .commit()
            }
        }
    }

    fun updatePostList(posts: List<Post>?) {
        if (!posts.isNullOrEmpty()) {
            values.clear()
            values.addAll(posts)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.userId.toString()
        holder.contentView.text = item.title

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
    }
}
