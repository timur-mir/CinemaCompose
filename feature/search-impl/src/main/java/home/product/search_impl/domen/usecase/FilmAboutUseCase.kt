package home.product.search_impl.domen.usecase

import home.product.home_impl.domain.model.response.FilmDetailInfo

import home.product.search_impl.domen.model.repository.FilmsRepository
import retrofit2.http.Path

class FilmAboutUseCase (private val repository: FilmsRepository
) {
    suspend fun getFilmById(@Path("id") id: Int): FilmDetailInfo {
        return repository.getFilmById(id)
    }
}