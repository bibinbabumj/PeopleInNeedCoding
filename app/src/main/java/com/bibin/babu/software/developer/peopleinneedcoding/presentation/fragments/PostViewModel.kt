package com.bibin.babu.software.developer.peopleinneedcoding.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.PostItem
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.PostRepository
import com.bibin.babu.software.developer.peopleinneedcoding.util.ResultErrorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<ResultErrorHandling<List<PostItem>>>()
    val posts: MutableLiveData<ResultErrorHandling<List<PostItem>>> get() = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        _posts.postValue(ResultErrorHandling.Loading)
        viewModelScope.launch {
            val result = repository.getPosts()
            result.onSuccess {

                _posts.postValue(ResultErrorHandling.Success(it))

            }
            result.onFailure {
                _posts.postValue(
                    ResultErrorHandling.Failure(
                        it.localizedMessage ?: "Something went wrong"
                    )
                )
            }
        }
    }
}