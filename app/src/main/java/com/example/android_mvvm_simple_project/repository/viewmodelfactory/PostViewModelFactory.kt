package com.example.android_mvvm_simple_project.repository.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_mvvm_simple_project.repository.PostRepository
import com.example.android_mvvm_simple_project.ui.viewmode.PostViewModel

class PostViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }


}