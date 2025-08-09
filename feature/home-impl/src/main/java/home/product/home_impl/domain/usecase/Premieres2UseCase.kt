package home.product.home_impl.domain.usecase

import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.domain.repository.MovieRepository

class Premieres2UseCase(
    private val repository: MovieRepository
) {
    suspend fun getPremieres(year: Int, month: String): PremieresList {
        return repository.getPremieres(year, month)
    }

}