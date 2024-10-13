package com.hajhosseinico.max.presentation.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.domain.usecase.home.HomeUseCases
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @MockK
    lateinit var homeUseCases: HomeUseCases

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        hiltRule.inject()

        viewModel = HomeViewModel(true, homeUseCases)
    }

    @Test
    fun `whenInternetIsAvailable_getFeatures_shouldFetchFromWebAndInsertIntoDatabase`() =
        runBlocking {
            val dummyFeatures = listOf(
                HomeFeatureModel(1, "title1", "desc1", "title", "desc"),
                HomeFeatureModel(2, "title2", "desc2", "title", "desc")
            )

            coEvery { homeUseCases.getHomeFeatures() } returns flowOf(dummyFeatures)
            coEvery { homeUseCases.insertHomeFeaturesIntoDatabase(any()) } returns flowOf(true)

            val result = viewModel.getFeatures().first()

            // Verifying data emitted
            assertEquals(dummyFeatures, result)

            // Verify each insertion call
            dummyFeatures.forEach {
                coVerify { homeUseCases.insertHomeFeaturesIntoDatabase(it) }
            }
        }

    @Test
    fun `whenInternetIsNotAvailable_getFeatures_shouldFetchFromLocalDatabase`() = runBlocking {
        viewModel = HomeViewModel(false, homeUseCases)

        val dummyFeatures = listOf(
            HomeFeatureModel(1, "title1", "desc1", "title", "desc"),
            HomeFeatureModel(2, "title2", "desc2", "title", "desc")
        )

        coEvery { homeUseCases.getHomeFeaturesFromRoom() } returns flowOf(dummyFeatures)

        val result = viewModel.getFeatures().first()

        assertEquals(dummyFeatures, result)
        coVerify(exactly = 0) { homeUseCases.insertHomeFeaturesIntoDatabase(any()) }
    }
}

