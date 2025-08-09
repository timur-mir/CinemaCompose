package home.product.home_impl.data.repository

import home.product.common.database.PremieresEntity
import home.product.common.database.PremieresRepository
import home.product.home_impl.data.mapper.toDomainFilmDetailInfo
import home.product.home_impl.data.mapper.toDomainFilmPresentOnNetPlatform
import home.product.home_impl.data.mapper.toDomainPremieresList
import home.product.home_impl.data.remote.MovieService
import home.product.home_impl.data.remote.response.Country
import home.product.home_impl.data.remote.response.Genre
import home.product.home_impl.data.remote.response.Movie
import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.domain.model.response.FilmNetSource
import home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform
import home.product.home_impl.domain.model.response.PremieresList

import home.product.home_impl.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.http.Path
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieService,
    val premieresRepository: PremieresRepository
) : MovieRepository {
    var premieresList: MutableList<PremieresEntity> = mutableListOf()
    var premieresListDao: MutableList<PremieresEntity> = mutableListOf()
    private val repoIOScope = CoroutineScope(Dispatchers.IO)
    private var downloader: Job? = null


    override suspend fun getPremieres(year: Int, month: String): PremieresList {
        val responseListFilms = api.premieres(year, month).toDomainPremieresList()
        return responseListFilms
    }

    private suspend fun getListForTransformation() {
        premieresListDao = premieresRepository.getAllPremieres() as MutableList<PremieresEntity>
    }


    override suspend fun getPremieres2(year: Int, month: String): PremieresList {
        return api.premieres2(year, month).toDomainPremieresList()
    }

    override suspend fun getPremieres3(year: Int, month: String): PremieresList {
        return api.premieres3(year, month).toDomainPremieresList()
    }

    override suspend fun getPremieresDao(): home.product.home_impl.data.remote.response.PremieresList {
        val responseListFilmsDao = mutableListOf<Movie>()
        getListForTransformation()
        delay(1000)
        for (item in premieresListDao) {
            responseListFilmsDao.add(
                Movie(
                    item.kinopoiskId!!,
                    item.nameRu,
                    item.posterUrl,
                    item.posterUrlPreview,
                    arrayListOf(Genre(item.genres)),
                    item.premiereRu,
                    arrayListOf(Country(item.countries)),
                    item.rating,
                    false,
                    item.filmId!!
                )
            )
        }
        return home.product.home_impl.data.remote.response.PremieresList(responseListFilmsDao.toList())
    }

    override suspend fun getFilmById(@Path("id") id: Int): FilmDetailInfo {
        return api.getFilmById(id)
    }

    override suspend fun getMoreUrlFilmOnNet(filmId: Int): FilmPresentOnNetPlatform {
        return api.getMoreUrlFilmOnNet(filmId).toDomainFilmPresentOnNetPlatform()
    }

    override suspend fun savePremieres1(list: List<PremieresEntity>) {
        repoIOScope.launch(Dispatchers.IO) {
            premieresRepository.savePremieres(list)
        }
    }
}

