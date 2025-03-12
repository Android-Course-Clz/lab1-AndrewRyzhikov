package com.example.vk.models

data class UserProfile(
    val avatarUrl: String,
    val name: String,
    val username: String,
    val followersCount: Int,
    val followingCount: Int,
    val postsCount: Int
)