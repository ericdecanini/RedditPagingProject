package com.ericdecanini.pagedplaceholderproject.api

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ericdecanini.pagedplaceholderproject.entity.RedditPost

class PostsDataSourceFactory: DataSource.Factory<String, RedditPost>() {

    val sourceLiveData = MutableLiveData<PostsDataSource>()

    override fun create(): DataSource<String, RedditPost> {
        val source = PostsDataSource()
        sourceLiveData.postValue(source)
        return source
    }
}