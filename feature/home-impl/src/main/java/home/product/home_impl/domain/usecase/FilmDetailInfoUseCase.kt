package home.product.home_impl.domain.usecase


import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.domain.repository.MovieRepository
import retrofit2.http.Path

class FilmDetailInfoUseCase (private val repository: MovieRepository
) {
    suspend fun getFilmById(@Path("id") id: Int): FilmDetailInfo {
        return repository.getFilmById(id)
    }
}