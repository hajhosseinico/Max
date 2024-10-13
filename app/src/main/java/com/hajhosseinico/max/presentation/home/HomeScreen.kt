package com.hajhosseinico.max.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hajhosseinico.max.presentation.Dimens.MediumPadding2
import com.hajhosseinico.max.presentation.Dimens.PageIndicatorWidth
import com.hajhosseinico.max.presentation.home.component.HomeState
import com.hajhosseinico.max.presentation.home.homefeatures.HomeFeatureList
import com.hajhosseinico.max.presentation.home.homepager.PageIndicatorDot
import com.hajhosseinico.max.presentation.home.homepager.PagerIndicatorText
import com.hajhosseinico.max.uicore.ToolbarView
import com.hajhosseinico.max.uicore.ViewPager
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val viewModel: HomeViewModel = hiltViewModel()

    // Collect state directly with .value
    val pagesState = viewModel.getHomePages().collectAsState(initial = emptyList())
    val featuresState = viewModel.getFeatures().collectAsState(initial = emptyList())

    // Access .value explicitly
    val pages = pagesState.value
    val features = featuresState.value

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size // Use the reactive pages state directly
    }

    val screenHeight =
        LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density
    val eightyPercentHeight = screenHeight * 0.8f

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .nestedScroll(nestedScrollConnection)
            .background(Color.Black)
    ) {
        if (pages.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(eightyPercentHeight),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // ViewPager occupies 80% of the screen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(eightyPercentHeight)
            ) {
                ViewPager(
                    pages = pages,
                    pagerState = pagerState,
                    height = eightyPercentHeight
                )

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Toolbar at the top
                    ToolbarView(HomeState())

                    // Pager Indicator Text
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MediumPadding2)
                            .navigationBarsPadding(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PagerIndicatorText(
                            pages[pagerState.currentPage]
                        ) { index ->
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PageIndicatorDot(
                    modifier = Modifier
                        .width(PageIndicatorWidth),
                    pageSize = pages.size, selectedPage = pagerState.currentPage,
                )
            }
        }

        // Horizontal List Section Below the Pager Indicator
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HomeFeatureList(features = features) // Use the reactive features state directly
        }
    }
}
