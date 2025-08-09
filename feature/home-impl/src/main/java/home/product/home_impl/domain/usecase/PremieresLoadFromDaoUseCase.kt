package home.product.home_impl.domain.usecase

import home.product.home_impl.data.remote.response.PremieresList
import home.product.home_impl.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class PremieresLoadFromDaoUseCase(val repository: MovieRepository) {
    suspend fun getPremieresDao(): PremieresList {
        return repository.getPremieresDao()
    }
}