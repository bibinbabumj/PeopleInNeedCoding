package com.bibin.babu.software.developer.peopleinneedcoding.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import org.junit.jupiter.api.Assertions.*


import com.bibin.babu.software.developer.peopleinneedcoding.data.remote.api.ApiService
import com.bibin.babu.software.developer.peopleinneedcoding.data.remote.dto.PostResponseDto
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.PostItem
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.AnalyticsLogger
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.LogParam
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal class PostRepositoryImplTest {
    private lateinit var repositoryImpl: PostRepositoryImpl
    private lateinit var apiService: ApiService
    private lateinit var analyticsLogger: AnalyticsLogger

    @Before
    fun setUp() {
        apiService = mockk()
        analyticsLogger = mockk(relaxed = true)
        repositoryImpl = PostRepositoryImpl(apiService, analyticsLogger)

    }

    @Test
    fun `Response error , exception is logged`() = runBlocking {
        coEvery { apiService.getPostsApi() } throws mockk<HttpException> {
            coEvery { code() } returns 400
            coEvery { message() } returns "Bad Request"
        }
        val result = repositoryImpl.getPosts()
        assertThat(result.isFailure).isTrue()
        verify {
            analyticsLogger.logEvent(
                "http_error",
                LogParam("code", 400),
                LogParam("message", "Bad Request")
            )
        }
    }


    @Test
    fun `Response success , data is returned`() = runBlocking {
        val mockPosts = listOf(
            PostItem("Helo", 1, "Hai", 1),
            PostItem("Helo", 1, "Hai", 1),
            PostItem("Helo", 1, "Hai", 1),
            PostItem("Helo", 1, "Hai", 1),
        )
        val mockResponse = Response.success(PostResponseDto().apply { addAll(mockPosts) })

        coEvery {
            apiService.getPostsApi()
        } returns mockResponse

        val result = repositoryImpl.getPosts()
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(mockPosts)
        verify(exactly = 0){analyticsLogger.logEvent(any(),any())}
    }

    @Test
    fun `Empty Api response returns empty list`()=runBlocking {
        val mockResponse = Response.success(PostResponseDto())
        coEvery {
            apiService.getPostsApi()
        } returns mockResponse
        val result = repositoryImpl.getPosts()
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(emptyList())

    }

    @Test
    fun `Network error is logged and failure is returned`() = runBlocking {
        coEvery { apiService.getPostsApi() } throws IOException("Network Error")

        val result = repositoryImpl.getPosts()

        assertThat(result.isFailure).isTrue()
        verify {
            analyticsLogger.logEvent(
                "io_error",
                LogParam("message", "Network Error")
            )
        }
    }

    @Test
    fun `HTTP error is logged with correct code and message`() = runBlocking {
        coEvery { apiService.getPostsApi() } throws mockk<HttpException> {
            coEvery { code() } returns 500
            coEvery { message() } returns "Internal Server Error"
        }

        val result = repositoryImpl.getPosts()

        assertThat(result.isFailure).isTrue()
        verify {
            analyticsLogger.logEvent(
                "http_error",
                LogParam("code", 500),
                LogParam("message", "Internal Server Error")
            )
        }
    }

}