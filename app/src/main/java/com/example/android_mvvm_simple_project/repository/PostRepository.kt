package com.example.android_mvvm_simple_project.repository

import com.example.android_mvvm_simple_project.data.api.ApiService

class PostRepository(
    private val apiService: ApiService
) {
    suspend fun getPosts() = apiService.getPosts()
}