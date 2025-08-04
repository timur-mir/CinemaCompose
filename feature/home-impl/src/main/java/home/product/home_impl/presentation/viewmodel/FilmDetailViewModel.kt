package home.product.home_impl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.domain.usecase.FilmDetailInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel@Inject constructor(private val getFilmDetailInfoUseCase: FilmDetailInfoUseCase): ViewModel() {
    private var filmDetailInfoInit: FilmDetailInfo = FilmDetailInfo(
        kinopoiskId = 0, imdbId = "", nameRu = "",
        nameOriginal = "", year = 0, "", genres = ArrayList(), ratingImdb = "", webUrl = "",
        filmLength = 0, ratingAgeLimits = "", countries = ArrayList(), serial = false, description = "", type=""
    )
    private val _filmDetailInfo = MutableStateFlow<FilmDetailInfo>(filmDetailInfoInit)
    val filmDetailInfo: StateFlow<FilmDetailInfo> = _filmDetailInfo.asStateFlow()
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
}