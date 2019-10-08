package com.zemoga.zemogatest.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.model.Comment
import kotlinx.android.synthetic.main.comment_list_content.view.commentItem

/**
 *
 */
class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private var commentList = mutableListOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_list_content, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.commentView.text = commentList[position].name
    }

    override fun getItemCount() = commentList.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val commentView: TextView = view.commentItem
    }

    fun updateCommentList(comments: List<Comment>?) {
        if (!comments.isNullOrEmpty()) {
            this.commentList.clear()
            this.commentList.addAll(comments)
            notifyDataSetChanged()
        }
    }
}
