package com.hajhosseinico.max.presentation.home.homefeatures

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hajhosseinico.max.domain.model.HomeFeatureModel

@Composable
fun HomeFeatureList (features: List<HomeFeatureModel>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(features.size) { index ->
            FeatureItem(features[index])
        }
    }
}