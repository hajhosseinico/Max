package com.hajhosseinico.max.presentation.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hajhosseinico.max.domain.model.HomeFeatureModel
import com.hajhosseinico.max.presentation.home.homefeatures.FeatureItem
import com.hajhosseinico.max.presentation.mainactivity.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FeatureItemTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    // Use the ActivityScenarioRule to launch MainActivity
    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun featureItem_displaysCorrectData() {
        hiltRule.inject()  // Inject dependencies

        composeTestRule.setContent {
            FeatureItem(
                HomeFeatureModel(
                    id = 1,
                    title = "Sample Title",
                    description = "Sample Description",
                    backgroundUrl = "",
                    logo = ""
                )
            )
        }

        composeTestRule.onNodeWithText("Sample Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sample Description").assertIsDisplayed()
    }
}
