package com.hajhosseinico.max.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hajhosseinico.max.data.local.HomeFeatureDao
import com.hajhosseinico.max.data.local.HomePagerDao
import com.hajhosseinico.max.data.remote.api.ApiService
import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.model.HomePagerModel
import com.hajhosseinico.max.domain.model.toEntityModel
import com.hajhosseinico.max.domain.repository.HomePageRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeScreenRepositoryImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var apiService: ApiService
    private lateinit var homeFeatureDao: HomeFeatureDao
    private lateinit var homePagerDao: HomePagerDao

    private lateinit var repository: HomePageRepository

    @Before
    fun setUp() {
        apiService = mockk()
        homeFeatureDao = mockk()
        homePagerDao = mockk()
        repository = HomeScreenRepositoryImpl(apiService, homeFeatureDao, homePagerDao)
        hiltRule.inject()
    }

    @Test
    fun `insertHomePageDefaultIntoDatabase returns false on failure`() = runBlocking {
        val dummyPage = HomePagerModel(1, "backgroundUrl", "logoUrl", "title", "description")
        coEvery { homePagerDao.insert(dummyPage.toEntityModel()) } throws Exception("Insert failed")

        val result = repository.insertHomePageDefaultIntoDatabase(dummyPage).first()

        assertFalse(result)
        coVerify { homePagerDao.insert(dummyPage.toEntityModel()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getHomePageDefaults returns data on success`() = runBlocking {
        val dummyData = listOf(HomePagerModel(1, "backgroundUrl", "logoUrl", "title", "description"))
        coEvery { apiService.getHomePageList() } returns Response.success(dummyData)

        val result = repository.getHomePageDefaults().first()

        assertEquals(dummyData, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getHomePageDefaults throws exception on failure`() = runBlocking {
        coEvery { apiService.getHomePageList() } returns Response.error(
            404, ResponseBody.create("application/json".toMediaTypeOrNull(), "Not found")
        )

        val exception = assertThrows(Exception::class.java) {
            runBlocking {
                repository.getHomePageDefaults().first()
            }
        }

        assertTrue(exception.message!!.contains("Failed to fetch home page defaults"))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getHomeFeatures returns data on success`() = runBlocking {
        val dummyData = listOf(HomeFeatureModel(1, "featureTitle", "featureDescription", "title", "description"))
        coEvery { apiService.getFeatures() } returns Response.success(dummyData)

        val result = repository.getHomeFeatures().first()

        assertEquals(dummyData, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getHomeFeatures throws exception on failure`() = runBlocking {
        // Mock the API call to return an error response
        coEvery { apiService.getFeatures() } returns Response.error(
            404, ResponseBody.create("application/json".toMediaTypeOrNull(), "Not found")
        )

        // Use assertThrows to verify that an exception is thrown
        val exception = assertThrows(Exception::class.java) {
            runBlocking {
                repository.getHomeFeatures().first()
            }
        }

        // Verify the exception message (optional)
        assertTrue(exception.message?.contains("Failed to fetch") ?: false)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insertHomeFeatureIntoDatabase inserts data successfully`() = runBlocking {
        val dummyFeature = HomeFeatureModel(1, "featureTitle", "featureDescription", "title", "description")

        // Mock the insertion behavior with no exception (successful insertion)
        coEvery { homeFeatureDao.insert(dummyFeature.toEntityModel()) } returns Unit

        val result = repository.insertHomeFeatureIntoDatabase(dummyFeature).first()

        assertTrue(result)
        coVerify { homeFeatureDao.insert(dummyFeature.toEntityModel()) }
    }
}