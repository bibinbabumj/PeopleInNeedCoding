package com.bibin.babu.software.developer.peopleinneedcoding.data.remote.api

import com.bibin.babu.software.developer.peopleinneedcoding.data.remote.dto.PostResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPostsApi(): Response<PostResponseDto>
}