package com.example.vk.models

data class Post(
    val text: String,
    val imageUrl: String,
    val likesCount: Int,
    val commentsCount: Int
)