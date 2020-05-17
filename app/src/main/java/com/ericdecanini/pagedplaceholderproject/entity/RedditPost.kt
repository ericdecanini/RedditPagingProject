package com.ericdecanini.pagedplaceholderproject.entity

import com.google.gson.annotations.SerializedName

data class RedditPost(
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("selftext")
    val selftext: String,
    val url: String?
)