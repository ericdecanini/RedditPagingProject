package com.ericdecanini.pagedplaceholderproject.mvvm

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.ericdecanini.pagedplaceholderproject.api.PostsDataSourceFactory
import com.ericdecanini.pagedplaceholderproject.entity.RedditPost

class RedditRepository {

    private val sourceFactory = PostsDataSourceFactory()

    fun getPosts(): LiveData<PagedList<RedditPost>> {
        return sourceFactory.toLiveData(
            pageSize = 10
        )
    }
}