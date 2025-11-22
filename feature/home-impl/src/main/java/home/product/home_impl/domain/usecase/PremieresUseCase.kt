package home.product.home_impl.domain.usecase

import home.product.common.database.PremieresEntity
import home.product.home_impl.data.remote.response.PremieresList
import home.product.home_impl.data.repository.MovieRepositoryImpl

import home.product.home_impl.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class PremieresUseCase(
    private val repository: MovieRepository
) {
suspend fun getPremieres(year:Int,month:String):  home.product.home_impl.domain.model.response.PremieresList {
    return repository.getPremieres(year,month)
}
   }