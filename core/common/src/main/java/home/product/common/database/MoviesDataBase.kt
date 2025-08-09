package home.product.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import home.product.common.database.MoviesDataBase.Companion.DB_VERSION
import home.product.common.network.util.CountryConverter
import home.product.common.network.util.GenreConverter


@Database(
    entities = [PremieresEntity::class],
    version = DB_VERSION
)
abstract  class MoviesDataBase : RoomDatabase(){
    @TypeConverters(CountryConverter::class, GenreConverter::class)
    abstract fun getPremieresDao() :PremieresDao
    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "MoviesDataBase"
    }
}