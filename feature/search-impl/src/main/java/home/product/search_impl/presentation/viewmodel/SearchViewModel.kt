package home.product.search_impl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.search_impl.data.remote.response.Films
import home.product.search_impl.data.remote.response.FilmsList
import home.product.search_impl.domen.usecase.FilmAboutUseCase

import home.product.search_impl.domen.usecase.FilmsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val filmUseCase: FilmsUseCase,private val
filmAboutUseCase: FilmAboutUseCase) : ViewModel() {
    private var filmAboutInfoInit :FilmDetailInfo = FilmDetailInfo(
        kinopoiskId = 0, imdbId = "", nameRu = "",
        nameOriginal = "", year = 0, "", genres = ArrayList(), ratingImdb = "", webUrl = "",
        filmLength = 0, ratingAgeLimits = "", countries = ArrayList(), serial = false, description = "", type=""
    )
    private val _filmAboutInfo = MutableStateFlow<FilmDetailInfo>(filmAboutInfoInit)
    val filmAboutInfo: StateFlow<FilmDetailInfo> = _filmAboutInfo.asStateFlow()

    private val _searchResults =
        MutableStateFlow<FilmsList>(FilmsList(emptyList()))
    val searchResults: StateFlow<FilmsList> =
        _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    fun search(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Проверка валидности запроса
                if (query.isBlank()) {
                    _searchResults.value = FilmsList(emptyList())
                    return@launch
                } else {
                    _isLoading.value = false
                    _searchResults.value = filmUseCase.getFilms(query)
                }

            } catch (e: Exception) {
                //  handleError(e)
            } finally {

            }
        }
    }

    fun getFilmAboutInfo(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                filmAboutUseCase.getFilmById(filmId)

            }.fold(
                onSuccess = { _filmAboutInfo.value = it },
                onFailure = { Log.d("AboutFilm", it.message ?: "") }
            )
        }
    }

}