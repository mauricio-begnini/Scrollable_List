package com.example.scrollablelist.models

import androidx.annotation.DrawableRes

data class Contact(
    @DrawableRes val picture: Int,
    val name: String,
    val status: String,
)
