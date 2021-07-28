package com.andrey.appviewusers.model

data class UserResponse(
    val results: List<User>
)

data class UserInfo(
    val user: User,
    val seed: String,
    val version: String
)
