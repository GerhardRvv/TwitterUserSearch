package com.gerhard.twittersearch

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.gerhard.twittersearch.core.api.ApiTwitterDataService
import com.gerhard.twittersearch.core.api.model.TwitterUser
import com.gerhard.twittersearch.core.api.model.TwitterUserResponse
import com.gerhard.twittersearch.core.mapper.TwitterUserMapper
import com.gerhard.twittersearch.core.utils.Resource
import com.gerhard.twittersearch.search.repository.SearchUserRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchUserRepositoryTest {

    private val apiService: ApiTwitterDataService = mockk()
    private val userMapper: TwitterUserMapper = mockk()
    private val searchUserRepository: SearchUserRepository by lazy {
        SearchUserRepository(apiService, userMapper, dispatcher)
    }

    private val dispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `getTwitterUser should emit loading, then success`() = runBlocking {
        val screenName = "testuser"
        val response = mockk<TwitterUserResponse>()
        val mappedUser = mockk<TwitterUser>()
        coEvery { apiService.getTwitterUser(screenName) } returns response
        every { userMapper.map(response) } returns mappedUser
        val expected = Resource.success(mappedUser)
        val result = searchUserRepository.getTwitterUser(screenName).last()
        assertEquals(expected, result)
    }

    @Test
    fun `getTwitterUser should emit loading, then error`() = runBlocking {
        val screenName = "testuser"
        coEvery { apiService.getTwitterUser(screenName) }.throws(RuntimeException())
        val result = searchUserRepository.getTwitterUser(screenName).last()
        val expected = Resource.error<Throwable>(Throwable("Failed to fetch user"))
        assertEquals(expected.status, result.status)
    }
}