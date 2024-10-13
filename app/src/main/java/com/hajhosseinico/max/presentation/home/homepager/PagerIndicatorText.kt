package com.hajhosseinico.max.presentation.home.homepager

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hajhosseinico.max.domain.model.HomePagerModel
import com.hajhosseinico.max.presentation.home.HomeViewModel

@Composable
fun PagerIndicatorText(
    currentPage: HomePagerModel,
    onPageSelected: (HomePagerModel) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    var underlineWidth by remember { mutableFloatStateOf(0f) }
    val pages = remember { mutableStateOf(emptyList<HomePagerModel>()) }

    LaunchedEffect(viewModel) {
        viewModel.getHomePages().collect {
            pages.value = it
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        for (page in pages.value) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onPageSelected(page) }
            ) {
                Text(
                    text = page.title,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .onGloballyPositioned { coordinates ->
                            if (page == currentPage) {
                                underlineWidth = coordinates.size.width.toFloat()
                            }
                        },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = if (page == currentPage) {
                        MaterialTheme.typography.labelMedium
                    } else {
                        MaterialTheme.typography.labelSmall
                    },
                    color = if (page == currentPage) {
                        Color.White
                    } else {
                        Color.LightGray
                    }
                )
                if (page == currentPage) {
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .height(2.dp)
                            .width(with(LocalDensity.current) { underlineWidth.toDp() })
                            .background(Color.White)
                    )
                }
            }
        }
    }
}