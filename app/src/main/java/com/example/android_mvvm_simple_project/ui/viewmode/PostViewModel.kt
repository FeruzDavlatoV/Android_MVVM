package com.example.android_mvvm_simple_project.ui.viewmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_mvvm_simple_project.model.Post
import com.example.android_mvvm_simple_project.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.ruyeo.smarttecno.utils.UiStateList

class PostViewModel(
    private val repository: PostRepository
) : ViewModel() {

    private val _postSet = MutableStateFlow<UiStateList<Post>>(UiStateList.EMPTY)
    val post = _postSet

    fun getPosts() = viewModelScope.launch {
        _postSet.value = UiStateList.LOADING
        try {
            val posts = repository.getPosts()

            //Server bn ishlanganda kk buladi
//            if (posts.status){
//                _postSet.value = UiStateList.SUCCESS(posts)
//            }else {
//                _postSet.value = UiStateList.ERROR(posts.message, true)
//            }

            _postSet.value = UiStateList.SUCCESS(posts)

        } catch (e: Exception) {
            _postSet.value = UiStateList.ERROR(e.localizedMessage ?: "No connection")
        }
    }

}