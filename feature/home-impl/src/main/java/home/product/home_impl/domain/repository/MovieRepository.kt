package home.product.home_impl.domain.repository

import home.product.home_impl.domain.model.response.PremieresList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPremieres(year:Int,month:String):PremieresList
}