package com.andrey.appviewusers.model

import com.andrey.appviewusers.db.User

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

fun Result.toUser() = User(
    uuid = login.uuid,
    age = dob.age,
    date = dob.date,
    gender = gender,
    city = location.city,
    country = location.country,
    name = location.street.name,
    number = location.street.number,
    email = email,
    username = login.username,
    first = name.first,
    last = name.last,
    large = picture.large
)

