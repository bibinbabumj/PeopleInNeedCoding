package com.bibin.babu.software.developer.peopleinneedcoding.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.PostItem
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<PostItem>>()
    val posts: MutableLiveData<List<PostItem>> get() = _posts


    fun fetchPosts() {
        viewModelScope.launch {
            val result = repository.getPosts()
            result.onSuccess { _posts.postValue(it) }
            result.onFailure { _posts.postValue(emptyList()) } // Handle error case gracefully
        }
    }
}