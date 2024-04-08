package com.example.scrollablelist

import androidx.annotation.DrawableRes

data class Contact(
    @DrawableRes val picture: Int,
    val name: String,
    val status: String,
)
