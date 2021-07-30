package com.andrey.appviewusers.utils

import androidx.room.TypeConverter
import com.andrey.appviewusers.model.Dob
import java.util.*
import java.util.stream.Collectors

class ResultsConverter {

    @TypeConverter
    fun fromResults(results: Dob): String? {
        return results.getFullDob()
    }

    @TypeConverter
    fun toResults(data: String): List<String> {
        return data.split(" ")
    }
}
