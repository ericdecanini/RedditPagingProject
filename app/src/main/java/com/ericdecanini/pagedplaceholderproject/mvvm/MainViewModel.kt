package com.ericdecanini.pagedplaceholderproject.mvvm

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val redditRepository = RedditRepository()

    val posts = redditRepository.getPosts()

}