package com.example.android_mvvm_simple_project.data.api

import com.example.android_mvvm_simple_project.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

}