package com.andrey.appviewusers.model

data class UserResponse(
    val info: Info,
    val results: MutableList<Result>
)

data class Info(
    val seed: String,
    var result: Int,
    val page: Int,
    val version: String
)

