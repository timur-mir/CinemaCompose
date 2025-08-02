package home.product.home_impl.domain.repository


import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform
import home.product.home_impl.domain.model.response.PremieresList
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Path

interface MovieRepository {
    suspend fun getPremieres(year:Int,month:String):PremieresList
    suspend fun getFilmById(@Path("id") id: Int): FilmDetailInfo
    suspend fun getMoreUrlFilmOnNet( @Path("id") filmId: Int): FilmPresentOnNetPlatform
}