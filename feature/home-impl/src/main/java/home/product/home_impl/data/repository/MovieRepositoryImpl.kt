package home.product.home_impl.data.repository

import home.product.home_impl.data.mapper.toDomainFilmDetailInfo
import home.product.home_impl.data.mapper.toDomainFilmPresentOnNetPlatform
import home.product.home_impl.data.mapper.toDomainPremieresList
import home.product.home_impl.data.remote.MovieService
import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.domain.model.response.FilmNetSource
import home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform
import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.Path

class MovieRepositoryImpl(
    private val api: MovieService
) : MovieRepository {
    override suspend fun getPremieres(year: Int, month: String): PremieresList {
        return api.premieres(year, month).toDomainPremieresList()
    }

    override suspend fun getPremieres2(year: Int, month: String): PremieresList {
        return api.premieres2(year, month).toDomainPremieresList()
    }

    override suspend fun getPremieres3(year: Int, month: String): PremieresList {
        return api.premieres3(year, month).toDomainPremieresList()
    }

    override suspend fun getFilmById(@Path("id") id: Int): FilmDetailInfo {
        return api.getFilmById(id)
    }

    override suspend fun getMoreUrlFilmOnNet(filmId: Int): FilmPresentOnNetPlatform {
        return api.getMoreUrlFilmOnNet(filmId).toDomainFilmPresentOnNetPlatform()
    }

}

