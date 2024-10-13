package com.hajhosseinico.max.uicore

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.hajhosseinico.max.domain.model.HomePagerModel
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun ViewPager(pages: List<HomePagerModel>, pagerState: PagerState, height: Dp) {
    var currentImage by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(true) }

    // Auto-scroll animation
    LaunchedEffect(pagerState, pages.size) {
        if (pages.isNotEmpty()) {
            while (true) {
                delay(2000)
                val nextPage = (pagerState.currentPage + 1) % pages.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    // Image fade animation
    LaunchedEffect(pagerState.currentPage) {
        visible = false
        delay(300)
        currentImage = pages.getOrNull(pagerState.currentPage)?.backGroundUrl ?: ""
        visible = true
    }

    // Background Image with Fade Animation
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(durationMillis = 300)),
        exit = fadeOut(animationSpec = tween(durationMillis = 300))
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(currentImage)
                    .size(coil.size.Size.ORIGINAL)
                    .build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        )
    }

    // Main Content (Title, Description, Logo)
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) { index ->
        val page = pages[index]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Image(
                contentScale = ContentScale.Fit,
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(page.logo)
                        .size(coil.size.Size.ORIGINAL)
                        .build()
                ),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp)
            )
            Text(
                text = page.title,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = page.description,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(56.dp))
        }
    }
}