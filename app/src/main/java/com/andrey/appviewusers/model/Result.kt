package com.andrey.appviewusers.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
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
    @ColumnInfo(name = "first_name")
    val first: String,
    @ColumnInfo(name = "last_name")
    val last: String,
    //val title: String
) {
    fun getFullName(): String {
        return "$first $last"
    }
}

data class Login(
    //val md5: String,
    //val password: String,
    //val salt: String,
    //val sha1: String,
    //val sha256: String,
    val username: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val uuid: String
)

data class Dob(
    val age: Int,
    val date: String
)

data class Location(
    val city: String,
    //val coordinates: Coordinates,
    val country: String,
    //val postcode: Any,
    val state: String,
    val street: Street
    //val timezone: Timezone
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

data class Street(
    @ColumnInfo(name = "name_street")
    val name: String,
    @ColumnInfo(name = "number_street")
    val number: Int
) {
    fun getFullStreet(): String {
        return "$name $number"
    }
}

data class Timezone(
    val description: String,
    val offset: String
)

data class Picture(
    @ColumnInfo(name = "url_photo")
    val large: String
    //val medium: String,
    //val thumbnail: String
)
