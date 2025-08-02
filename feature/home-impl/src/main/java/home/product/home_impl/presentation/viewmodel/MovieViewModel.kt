package home.product.home_impl.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import home.product.home_impl.domain.model.response.Country
import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform
import home.product.home_impl.domain.model.response.Genre
import home.product.home_impl.domain.model.response.Movie
import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.domain.usecase.FilmDetailInfoUseCase
import home.product.home_impl.domain.usecase.PremieresUseCase
import home.product.home_impl.domain.usecase.WebViewUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPremieresUseCase: PremieresUseCase,
    private val getFilmDetailInfoUseCase: FilmDetailInfoUseCase,
    private val getWebViewUseCase: WebViewUseCase
) : ViewModel() {
    private var downloader: Job? = null
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _premieresInfo = MutableStateFlow<PremieresList>(PremieresList(emptyList()))
    val premieresInfo: StateFlow<PremieresList> = _premieresInfo.asStateFlow()
    private val _premieresInfo2 = MutableStateFlow<PremieresList>(PremieresList(emptyList()))
    val premieresInfo2: StateFlow<PremieresList> = _premieresInfo2.asStateFlow()
    private val _premieresInfo3 = MutableStateFlow<PremieresList>(PremieresList(emptyList()))
    val premieresInfo3: StateFlow<PremieresList> = _premieresInfo3.asStateFlow()
    private val _moveNetList =
        MutableStateFlow<FilmPresentOnNetPlatform>(FilmPresentOnNetPlatform(0))
    val moveNetList: StateFlow<FilmPresentOnNetPlatform> = _moveNetList.asStateFlow()

    private val _filmDetailInfo = MutableStateFlow<FilmDetailInfo>(
        FilmDetailInfo(
            462654,
            "tt1226837",
            "Красивый мальчик",
            "Beautiful Boy",
            2018,
            "https://kinopoiskapiunofficial.tech/images/posters/kp/462654.jpg",
            arrayListOf(Genre("драма"), Genre("биография")),
            "7.3",
            120,
            "18+",
            false,
            arrayListOf(Country("Сша")),
            " Дэвид Шефф переживает трагедию: его милый и очаровательный сын Ник стал" +
                    "наркоманом. Откуда взялась пагубная привычка? Ник растёт в любящей семье, он" +
                    " отлично учится, ни в чём не нуждается. Развод родителей прошёл спокойно." +
                    "С матерью, живущей в Лос-Анджелесе Ник общается до сих пор." +
                    " Пытаясь найти ответы, Дэвид вспоминает, каким ребёнок был раньше — вдумчивым" +
                    " и красивым мальчиком.",
            "FILM"
        )
    )
    val filmDetailInfo: StateFlow<FilmDetailInfo> = _filmDetailInfo.asStateFlow()

    init {
    }

fun getMoviesUrlFromNet(filmId:Int){
    viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
           getWebViewUseCase.getMoreUrlFilmOnNet(filmId)

        }.fold(
            onSuccess = { _moveNetList.value = it },
            onFailure = { Log.d("GetFilmNet", it.message ?: "") }
        )
    }
}
    fun getFilmDetailInfo(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getFilmDetailInfoUseCase.getFilmById(filmId)

            }.fold(
                onSuccess = { _filmDetailInfo.value = it },
                onFailure = { Log.d("GetFilmDetailInfoViewModel", it.message ?: "") }
            )
        }
    }

    fun loadPremieres() {
        downloader ?: viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                getPremieresUseCase.getPremieres(2025, "august")
            }.fold(
                onSuccess = { response ->
                    _premieresInfo.value = response
                },
                onFailure = {
                    Log.d("JJJ", it.message ?: "")
                }
            )
            _isLoading.value = false
            downloader?.cancel()
            downloader = null
        }
    }

    fun loadPremieres2() {
        downloader ?: viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                getPremieresUseCase.getPremieres(2025, "september")
            }.fold(
                onSuccess = { response ->
                    _premieresInfo2.value = response
                },
                onFailure = {
                    Log.d("JJT", it.message ?: "")
                }
            )
            _isLoading.value = false
            downloader?.cancel()
            downloader = null
        }
    }

    fun loadPremieres3() {
        downloader ?: viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                getPremieresUseCase.getPremieres(2025, "november")
            }.fold(
                onSuccess = { response ->
                    _premieresInfo3.value = response
                },
                onFailure = {
                    Log.d("JJR", it.message ?: "")
                }
            )
            _isLoading.value = false
            downloader?.cancel()
            downloader = null
        }
    }
}