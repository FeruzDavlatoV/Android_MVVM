package com.example.android_mvvm_simple_project.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.android_mvvm_simple_project.R
import com.example.android_mvvm_simple_project.data.api.ApiClient
import com.example.android_mvvm_simple_project.data.api.ApiService
import com.example.android_mvvm_simple_project.repository.PostRepository
import com.example.android_mvvm_simple_project.repository.viewmodelfactory.PostViewModelFactory
import com.example.android_mvvm_simple_project.ui.viewmode.PostViewModel
import me.ruyeo.smarttecno.utils.UiStateList


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var  viewModel: PostViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        viewModel.getPosts()
        setupUI()
    }

    private fun setupUI() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.post.collect{
                when(it){
                    is UiStateList.LOADING -> {
                        //show progress
                    }

                    is UiStateList.SUCCESS -> {
                        Log.d("TAG", "setupUI: ${it.data}")
                    }

                    is UiStateList.ERROR -> {
                        Log.d("TAG", "setupUI: ${it.message}")
                    }else -> Unit
                }
            }
        }
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this,
        PostViewModelFactory(PostRepository(ApiClient.createService(ApiService::class.java)))
        )[PostViewModel::class.java]
    }
}