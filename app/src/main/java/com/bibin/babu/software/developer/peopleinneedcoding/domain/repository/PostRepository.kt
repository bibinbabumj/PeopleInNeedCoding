package com.bibin.babu.software.developer.peopleinneedcoding.domain.repository

import com.bibin.babu.software.developer.peopleinneedcoding.data.local.UserDao
import com.bibin.babu.software.developer.peopleinneedcoding.data.remote.dto.PostResponseDto
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.PostItem
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import okhttp3.Response

interface PostRepository {
    suspend fun getPosts(): Result<List<PostItem>>
    suspend fun insertUser(user: UserModel)
    fun getAllUserDb(): Flow<List<UserModel>>
}