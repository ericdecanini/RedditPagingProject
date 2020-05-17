package com.ericdecanini.pagedplaceholderproject.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ericdecanini.pagedplaceholderproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel by viewModels()
        this.viewModel = viewModel

        observePosts()
    }

    private fun observePosts() {
        val adapter = PostsAdapter()
        posts_recycler.adapter = adapter
        viewModel.posts.observe(this, Observer {
            adapter.submitList(it) {}
        })
    }
}
