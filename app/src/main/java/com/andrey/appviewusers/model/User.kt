package com.andrey.appviewusers.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User (
    val login: Login,
    val username: String,
    val name: Name,
    val location: Location,
    val gender: String,
    val dob: String,
    val picture: Picture
        )

data class Name(
    val first: String,
    val last: String,
    val title: String
) {
    fun getFullName(): String {
        return "$first $last"
    }
}
data class Login(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val uuid: String,
    val email: String
)

data class Dob(
    val date: String,
    val age: Int
)

data class Location(
    val city: String,
    val state: String,
    val street: String
)

data class Picture(
    val large: String
)

