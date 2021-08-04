package com.andrey.appviewusers.model

import androidx.room.*

data class Result (
    val dob: Dob,
    val gender: String,
    val location: Location,
    val email: String,
    val login: Login,
    val name: Name,
    val picture: Picture
        )

data class Name(
    val first: String,
    val last: String
) {
    fun getFullName(): String {
        return "$first $last"
    }
}

data class Login(
    val username: String,
    val uuid: String
)

data class Dob(
    val age: Int,
    val date: String
)

data class Location(
    val city: String,
    val country: String,
    val street: Street
)

data class Street(
    val name: String,
    val number: Int
) {
    fun getFullStreet(): String {
        return "$name $number"
    }
}

data class Picture(
    val large: String
)

