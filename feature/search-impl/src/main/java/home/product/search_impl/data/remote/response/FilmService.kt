package home.product.search_impl.data.remote.response

import home.product.home_impl.domain.model.response.FilmDetailInfo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.1/films/search-by-keyword")
    suspend fun getFilms(@Query("keyword") keyword: String,@Query("page") page:Int): FilmsList

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/{id}")
    suspend fun getFilmById(@Path("id") id: Int): FilmDetailInfo

    private companion object {
        private const val api_key = "55ecd8a9-9cca-4d49-bbdb-e1a7658a012d"
    }
}