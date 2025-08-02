package home.product.home_impl.domain.usecase

import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform
import home.product.home_impl.domain.repository.MovieRepository
import retrofit2.http.Path

class WebViewUseCase(private val repository: MovieRepository)
 {
    suspend fun getMoreUrlFilmOnNet( filmId: Int): FilmPresentOnNetPlatform{
        return repository.getMoreUrlFilmOnNet(filmId)
    }
}