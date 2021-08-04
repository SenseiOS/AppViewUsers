package com.andrey.appviewusers.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/*@Entity(tableName = "Users", primaryKeys = ["id"])
data class Result (
    @Embedded
    val dob: Dob,
    val gender: String,
    @Embedded
    val location: Location,
    val email: String,
    @Embedded
    val login: Login,
    @Embedded
    val name: Name,
    @Embedded
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
    val country: String,
    @Embedded
    val street: Street
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

data class Picture(
    @ColumnInfo(name = "url_photo")
    val large: String
)
*/
