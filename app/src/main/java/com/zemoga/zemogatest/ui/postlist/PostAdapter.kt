package com.zemoga.zemogatest.ui.postlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.model.Post
import kotlinx.android.synthetic.main.post_list_content.view.*

/**
 *
 */
class PostAdapter(
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var posts = mutableListOf<Post>()

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_content, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.idView.text = post.userId.toString()
        holder.contentView.text = post.title
    }

    override fun getItemCount() = posts.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content

        init {
            view.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    fun updatePostList(posts: List<Post>?) {
        if (!posts.isNullOrEmpty()) {
            this.posts.clear()
            this.posts.addAll(posts)
            notifyDataSetChanged()
        }
    }
}
