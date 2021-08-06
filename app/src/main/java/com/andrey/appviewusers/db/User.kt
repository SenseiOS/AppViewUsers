package com.andrey.appviewusers.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val uuid: String,

    val age: Int,

    val date: String,

    val gender: String,

    val city: String,

    val country: String,

    @ColumnInfo(name = "name_street")
    val name: String,

    @ColumnInfo(name = "number_street")
    val number: Int,

    val email: String,

    val username: String,

    @ColumnInfo(name = "first_name")
    val first: String,

    @ColumnInfo(name = "last_name")
    val last: String,

    @ColumnInfo(name = "url_photo")
    val large: String
) {
    fun getFullStreet(): String {
        return "$name $number"
    }

    fun getFullName(): String {
        return "$first $last"
    }
}
