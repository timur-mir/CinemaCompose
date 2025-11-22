package home.product.search_impl.domen.usecase

import home.product.search_impl.data.remote.response.FilmsList
import home.product.search_impl.domen.model.repository.FilmsRepository


class FilmsUseCase(
    private val repository: FilmsRepository
) {
    suspend fun getFilms(keyword: String): FilmsList {
        return repository.getFilms(keyword)
    }
}