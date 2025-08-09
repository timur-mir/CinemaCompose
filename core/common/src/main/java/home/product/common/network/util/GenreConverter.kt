package home.product.common.network.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import home.product.common.database.model.Genre


class GenreConverter {
    private val gson: Gson by lazy { Gson() }
    @TypeConverter
    fun toGenreList(jsonGenre: String): List<Genre>? {
        if(jsonGenre==null)
            return null
        val notesType = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(jsonGenre, notesType) as List<Genre>
    }
    @TypeConverter
    fun fromGenreGson(genres: List<Genre>?): String? {
        if (genres == null)
            return null
        val notesType = object : TypeToken<List<Genre>>() {}.type
        return gson.toJson(genres, notesType)


    }
}