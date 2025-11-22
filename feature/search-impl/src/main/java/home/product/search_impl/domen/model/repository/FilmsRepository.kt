package home.product.search_impl.domen.model.repository

import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.search_impl.data.remote.response.Films
import home.product.search_impl.data.remote.response.FilmsList
import retrofit2.http.Path

import retrofit2.http.Query

interface FilmsRepository {
    suspend fun getFilms(@Query("keyword") keyword: String): FilmsList
    suspend fun getFilmById(@Path("id") id: Int): FilmDetailInfo
}