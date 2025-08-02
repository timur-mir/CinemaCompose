package home.product.home_impl.data.remote


import home.product.home_impl.data.remote.response.FilmPresentOnNetPlatform
import home.product.home_impl.data.remote.response.PremieresList
import home.product.home_impl.domain.model.response.FilmDetailInfo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/premieres")
    suspend fun premieres(@Query("year") year: Int, @Query("month") month: String): PremieresList
    private companion object {
        private const val api_key = "ce993e3d-935a-4c1c-ba3e-dacd2537cb3c"
    }

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/{id}")
    suspend fun getFilmById(@Path("id") id: Int): FilmDetailInfo

    @GET("/api/v2.2/films/{id}/videos")
    suspend fun getMoreUrlFilmOnNet(
        @Path("id") filmId: Int
    ): FilmPresentOnNetPlatform
}
