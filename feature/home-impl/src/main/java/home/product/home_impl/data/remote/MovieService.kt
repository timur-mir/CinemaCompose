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

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/premieres")
    suspend fun premieres2(@Query("year") year: Int, @Query("month") month: String): PremieresList

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/premieres")
    suspend fun premieres3(@Query("year") year: Int, @Query("month") month: String): PremieresList

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/{id}")
    suspend fun getFilmById(@Path("id") id: Int): FilmDetailInfo


    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/{id}/videos")
    suspend fun getMoreUrlFilmOnNet(
        @Path("id") filmId: Int
    ): FilmPresentOnNetPlatform

    private companion object {
        private const val api_key = "55ecd8a9-9cca-4d49-bbdb-e1a7658a012d"
    }
}
