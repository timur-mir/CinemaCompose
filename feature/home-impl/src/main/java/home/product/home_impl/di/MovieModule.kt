package home.product.home_impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import home.product.common.database.PremieresRepository
import home.product.home_impl.data.remote.MovieService
import home.product.home_impl.data.repository.MovieRepositoryImpl
import home.product.home_impl.domain.repository.MovieRepository
import home.product.home_impl.domain.usecase.FilmDetailInfoUseCase
import home.product.home_impl.domain.usecase.Premieres2UseCase
import home.product.home_impl.domain.usecase.Premieres3UseCase
import home.product.home_impl.domain.usecase.PremieresLoadFromDaoUseCase
import home.product.home_impl.domain.usecase.PremieresSaveToDaoUseCase
import home.product.home_impl.domain.usecase.PremieresUseCase
import home.product.home_impl.domain.usecase.WebViewUseCase
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
    fun provideMovieRepository(api: MovieService,premieresRepository: PremieresRepository): MovieRepository {
        return MovieRepositoryImpl(api,premieresRepository)
    }
    @Provides
    @Singleton
    fun providePremieresSaveToDao(repository: MovieRepository):PremieresSaveToDaoUseCase{
        return PremieresSaveToDaoUseCase(repository)
    }
    @Provides
    @Singleton
    fun providePremieresLoadFromDao(repository: MovieRepository):PremieresLoadFromDaoUseCase{
        return PremieresLoadFromDaoUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePremieresUseCase(repository: MovieRepository): PremieresUseCase {
        return PremieresUseCase(repository)
    }
    @Provides
    @Singleton
    fun providePremieres2UseCase(repository: MovieRepository): Premieres2UseCase {
        return Premieres2UseCase(repository)
    }
    @Provides
    @Singleton
    fun providePremieres3UseCase(repository: MovieRepository): Premieres3UseCase {
        return Premieres3UseCase(repository)
    }
    @Provides
    @Singleton
    fun provideFilmDetailInfoUseCase(repository: MovieRepository): FilmDetailInfoUseCase {
        return FilmDetailInfoUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideWebViewUseCase(repository: MovieRepository): WebViewUseCase {
        return WebViewUseCase(repository)
    }
}