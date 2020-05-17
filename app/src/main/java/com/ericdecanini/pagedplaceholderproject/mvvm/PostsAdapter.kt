package com.ericdecanini.pagedplaceholderproject.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericdecanini.pagedplaceholderproject.R
import com.ericdecanini.pagedplaceholderproject.entity.RedditPost
import kotlinx.android.synthetic.main.list_item_post.view.*

class PostsAdapter: PagedListAdapter<RedditPost, PostsAdapter.PostViewHolder>(POST_COMPARATOR) {

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<RedditPost>() {
            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_post, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(redditPost: RedditPost?) {
            itemView.title.text = redditPost?.title
            itemView.body.text = if (redditPost?.selftext?.isNotEmpty() == true)
                redditPost.selftext else redditPost?.url
        }
    }
}