package home.product.search_impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import home.product.home_impl.domain.repository.MovieRepository
import home.product.home_impl.domain.usecase.FilmDetailInfoUseCase
import home.product.search_impl.data.remote.response.FilmService
import home.product.search_impl.data.repository.FilmRepositoryImpl
import home.product.search_impl.domen.model.repository.FilmsRepository
import home.product.search_impl.domen.usecase.FilmAboutUseCase
import home.product.search_impl.domen.usecase.FilmsUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FilmModule {
    @Provides
    @Singleton
    fun provideFilmService(retrofit: Retrofit): FilmService = retrofit.create(
        FilmService::class.java
    )
    @Provides
    @Singleton
    fun provideFilmRepository(api:FilmService): FilmsRepository {
        return FilmRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideFilmAboutUseCase(repository:FilmsRepository): FilmAboutUseCase{
        return FilmAboutUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideFilmsUseCase(repository: FilmsRepository): FilmsUseCase {
        return FilmsUseCase(repository)
    }
}