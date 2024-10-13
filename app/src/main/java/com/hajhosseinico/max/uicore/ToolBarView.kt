package com.hajhosseinico.max.uicore

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hajhosseinico.max.R
import com.hajhosseinico.max.presentation.home.component.HomeState
import com.hajhosseinico.max.ui.theme.MaxTheme

@Composable
fun ToolbarView(homeState: HomeState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo on the left
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(48.dp)

                .align(Alignment.CenterVertically)
        )

        // Spacer to push icons to the right
        Spacer(modifier = Modifier.weight(1f))

        // Share Icon
        IconButton(onClick = { /* Handle share click */ }) {
            Icon(
                imageVector = Icons.Filled.Share,
                tint = Color.White,
                contentDescription = "Share"
            )
        }

        // Notification Icon with an indicator dot
        IconButton(onClick = { /* Handle notification click */ }) {
            Box {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
                if (homeState.notificationCount >= 0) {
                    // Green dot indicating notifications
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                color = colorResource(id = R.color.red),
                                shape = CircleShape
                            )
                            .align(Alignment.TopStart)
                            .offset(
                                x = (-2).dp,
                                y = 2.dp
                            ) // Positioning the dot on the top-left corner
                    )
                }
            }
        }

        // Profile Icon with rounded shape
        IconButton(onClick = { /* Handle profile click */ }) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToolbarViewPreview(){
    MaxTheme() {
        ToolbarView(
            HomeState()
        )
    }
}
