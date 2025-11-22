package home.product.home_impl.domain.usecase

import home.product.common.database.PremieresEntity
import home.product.home_impl.domain.repository.MovieRepository

class PremieresSaveToDaoUseCase(val repository: MovieRepository) {
    suspend fun savePremieresDao(list: List<PremieresEntity>){
        repository.savePremieres1(list)
    }
}