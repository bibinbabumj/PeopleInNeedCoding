package com.bibin.babu.software.developer.peopleinneedcoding.domain.repository

import com.bibin.babu.software.developer.peopleinneedcoding.data.remote.dto.PostResponseDto
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.PostItem
import okhttp3.Response

interface PostRepository {
    suspend fun getPosts(): Result<List<PostItem>>
}