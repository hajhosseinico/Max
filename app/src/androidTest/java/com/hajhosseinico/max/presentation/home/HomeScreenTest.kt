package com.hajhosseinico.max.presentation.home

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hajhosseinico.max.presentation.mainactivity.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import com.hajhosseinico.max.domain.model.HomeFeatureModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun homeScreen_displaysFeatureItems_whenDataIsLoaded() {
        composeTestRule.waitUntil(15_000) {
            composeTestRule.onAllNodes(hasTestTag("FeatureItem-1")).fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("FeatureItem-1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sample Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sample Description").assertIsDisplayed()
    }
}
