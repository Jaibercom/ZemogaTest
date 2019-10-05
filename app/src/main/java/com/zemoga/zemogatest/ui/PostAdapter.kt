package com.zemoga.zemogatest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zemoga.zemogatest.R
import com.zemoga.zemogatest.model.Post
import kotlinx.android.synthetic.main.post_list_content.view.*
import timber.log.Timber

/**
 *
 */
class PostAdapter(
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var posts = mutableListOf<Post>()

//    private var onItemClickListener: OnItemClickListener

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


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {

            view?.setOnClickListener({
                Timber.d("onClick position: $adapterPosition")
                clickListener.onItemClick(adapterPosition)
            })
        }
    }

//    override fun onClick(view: View ){
//        val post = view.get
//
//        this.postViewModel.setPosition(post)
//
//        val fragment = PostDetailFragment.newInstance(post.userId)
//
//        parentActivity.supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("PostsDetailFragment")
//            .replace(R.id.fragmentContainer, fragment)
//            .commit()
//    }

    fun updatePostList(posts: List<Post>?) {
        if (!posts.isNullOrEmpty()) {
            this.posts.clear()
            this.posts.addAll(posts)
            notifyDataSetChanged()
        }
    }
}
