package home.product.search_impl.data.repository


import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.search_impl.data.remote.response.FilmService
import home.product.search_impl.data.remote.response.FilmsList
import home.product.search_impl.domen.model.repository.FilmsRepository

import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: FilmService
) : FilmsRepository {
    override suspend fun getFilms(keyword: String): FilmsList {
        return api.getFilms(keyword,1)
    }

    override suspend fun getFilmById(id: Int): FilmDetailInfo {
        return api.getFilmById(id)
    }
}