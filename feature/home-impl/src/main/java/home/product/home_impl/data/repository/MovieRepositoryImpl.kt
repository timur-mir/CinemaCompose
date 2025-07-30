package home.product.home_impl.data.repository

import home.product.home_impl.data.mapper.toDomainPremieresList
import home.product.home_impl.data.remote.MovieService
import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val api: MovieService
) : MovieRepository {
    override suspend fun getPremieres(year: Int, month: String): PremieresList {
        return api.premieres(year, month).toDomainPremieresList()
    }
}

