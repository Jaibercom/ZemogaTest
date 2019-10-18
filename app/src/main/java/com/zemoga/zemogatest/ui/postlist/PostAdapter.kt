package com.zemoga.zemogatest.ui.postlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.model.Post
import kotlinx.android.synthetic.main.post_list_content.view.blue_dot
import kotlinx.android.synthetic.main.post_list_content.view.content
import kotlinx.android.synthetic.main.post_list_content.view.star

/**
 * Post Adapter class
 */
class PostAdapter(
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var deletedPostPosition: Int = 0
    private lateinit var deletedPost: Post
    private var postList = mutableListOf<Post>()

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_content, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]

        if (post.isRead) {
            holder.dot.visibility = View.INVISIBLE
        } else {
            holder.dot.visibility = View.VISIBLE
        }

        if (post.isFavorite) {
            holder.starView.visibility = View.VISIBLE
        } else {
            holder.starView.visibility = View.INVISIBLE
        }

        holder.contentView.text = post.title
    }

    override fun getItemCount() = postList.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dot: TextView = view.blue_dot
        val contentView: TextView = view.content
        val starView: TextView = view.star

        init {
            view.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    fun updatePostList(posts: List<Post>?) {
        this.postList.clear()
        posts?.let { this.postList.addAll(it) }
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int, view: View) {
        deletedPost = postList[position]
        deletedPostPosition = position
        this.postList.removeAt(position)
        notifyItemRemoved(position)
        showUndoSnackbar(view)
    }

    private fun showUndoSnackbar(view: View) {
        val recoverPostSnackbar = Snackbar.make(
            view, R.string.snack_bar_text,
            Snackbar.LENGTH_LONG
        )
        recoverPostSnackbar.apply {
            setAction(R.string.snack_bar_undo) { undoDelete() }
            show()
        }
    }

    private fun undoDelete() {
        postList.add(deletedPostPosition, deletedPost)
        notifyItemInserted(deletedPostPosition)
    }
}
