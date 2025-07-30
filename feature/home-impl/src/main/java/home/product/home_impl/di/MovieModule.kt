package home.product.home_impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import home.product.home_impl.data.remote.MovieService
import home.product.home_impl.data.repository.MovieRepositoryImpl
import home.product.home_impl.domain.repository.MovieRepository
import home.product.home_impl.domain.usecase.PremieresUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(
        MovieService::class.java
    )

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieService): MovieRepository {
        return MovieRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePremieresUseCase(repository: MovieRepository): PremieresUseCase {
        return PremieresUseCase(repository)
    }
}