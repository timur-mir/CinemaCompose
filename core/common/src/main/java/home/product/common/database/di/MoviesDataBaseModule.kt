package home.product.common.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import home.product.common.database.MoviesDataBase
import home.product.common.database.PremieresDao
import home.product.common.database.PremieresRepository
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object MoviesDataBaseModule {
    @Provides
    @Singleton
    fun provideMoviesDataBase(context: Context): MoviesDataBase {
        return Room.databaseBuilder(context, MoviesDataBase::class.java, MoviesDataBase.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideMainDao(db: MoviesDataBase) = db.getPremieresDao()


    @Singleton
    @Provides
    fun providePremieresRepository(dao: PremieresDao): PremieresRepository {
        return PremieresRepository(dao)
    }
}