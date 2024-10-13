package com.hajhosseinico.max.presentation.home.component

data class HomeState(
    val title: String = "",
    val selectedPosition: Int = 0,
    val isLoading: Boolean = false,
    val notificationCount: Int = 0
)