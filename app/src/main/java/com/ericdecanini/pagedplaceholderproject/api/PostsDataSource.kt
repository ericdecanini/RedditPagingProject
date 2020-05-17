package com.ericdecanini.pagedplaceholderproject.api

import androidx.paging.PageKeyedDataSource
import com.ericdecanini.pagedplaceholderproject.entity.RedditPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsDataSource: PageKeyedDataSource<String, RedditPost>() {

    private val redditApi: RedditApi = Retrofit.Builder()
        .baseUrl("https://www.reddit.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RedditApi::class.java)


    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, RedditPost>
    ) {
        val request = redditApi.getTop("androiddev", params.requestedLoadSize)
        val response = request.execute()
        val data = response.body()?.data
        val items = data?.children?.map { it.data } ?: emptyList()
        callback.onResult(items, data?.before, data?.after)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, RedditPost>) {
        redditApi.getTopAfter(
            "androiddev",
            params.key,
            params.requestedLoadSize
        ).enqueue(object: Callback<RedditApi.ListingResponse> {

            override fun onResponse(
                call: Call<RedditApi.ListingResponse>,
                response: Response<RedditApi.ListingResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    val items = data?.children?.map { it.data } ?: emptyList()
                    callback.onResult(items, data?.after)
                }
            }

            override fun onFailure(call: Call<RedditApi.ListingResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, RedditPost>
    ) {

    }

}