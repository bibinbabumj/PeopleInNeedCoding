package com.bibin.babu.software.developer.peopleinneedcoding.data.repository

import com.bibin.babu.software.developer.peopleinneedcoding.data.local.UserDao
import com.bibin.babu.software.developer.peopleinneedcoding.data.remote.api.ApiService
import com.bibin.babu.software.developer.peopleinneedcoding.data.remote.dto.PostResponseDto
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.PostItem
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.UserModel
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.AnalyticsLogger
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.LogParam
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class PostRepositoryImpl(
    private val api: ApiService, private val analyticsLogger: AnalyticsLogger,
    private val userDao: UserDao
) : PostRepository {
    //override suspend fun getPosts(): List<PostItem>? = api.getPostsApi().body()

    override suspend fun getPosts(): Result<List<PostItem>> {
        return try {
            val response = api.getPostsApi()
            if (response.isSuccessful) {
                // Return successful result
                Result.success(response.body() ?: emptyList())
            } else {
                // Log HTTP error and return failure
                analyticsLogger.logEvent(
                    "http_error",
                    LogParam("code", response.code()),
                    LogParam("message", response.message())
                )
                Result.failure(HttpException(response))
            }
        } catch (e: HttpException) {
            // Log HTTP error and return failure
            analyticsLogger.logEvent(
                "http_error",
                LogParam("code", e.code()),
                LogParam("message", e.message())
            )
            Result.failure(e)
        } catch (e: IOException) {
            // Log IO error and return failure
            analyticsLogger.logEvent(
                "io_error",
                LogParam("message", e.message.toString())
            )
            Result.failure(e)
        } catch (e: Exception) {
            // Handle cancellation exceptions and other unexpected errors
            if (e is CancellationException) throw e
            analyticsLogger.logEvent(
                "generic_error",
                LogParam("message", e.message.toString())
            )
            Result.failure(e)
        }
    }

    override suspend fun insertUser(user: UserModel) = userDao.insertUser(user)


    override fun getAllUserDb(): Flow<List<UserModel>> = userDao.getAllUsers()
}