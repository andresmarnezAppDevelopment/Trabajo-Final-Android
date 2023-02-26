package net.iessochoa.grupof.practicafinalandroid.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromSongList(value: List<Long>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Long>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSongList(value: String): List<Long> {
        val gson = Gson()
        val type = object : TypeToken<List<Long>>() {}.type
        return gson.fromJson(value, type)
    }
}