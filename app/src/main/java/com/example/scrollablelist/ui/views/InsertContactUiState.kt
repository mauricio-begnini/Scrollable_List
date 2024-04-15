package com.example.scrollablelist.ui.views

import androidx.annotation.DrawableRes
import com.example.scrollablelist.R

data class InsertContactUiState(
    @DrawableRes val picture: Int = R.drawable.baseline_face_24,
    val name: String = "",
    val status: String = "",
)
