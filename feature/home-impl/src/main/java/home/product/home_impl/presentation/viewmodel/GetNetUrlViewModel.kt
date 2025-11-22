package home.product.home_impl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform
import home.product.home_impl.domain.usecase.WebViewUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetNetUrlViewModel @Inject constructor(private val getWebViewUseCase: WebViewUseCase) :
    ViewModel() {
    private val _moveNetList =
        MutableStateFlow<FilmPresentOnNetPlatform>(FilmPresentOnNetPlatform(0))
    val moveNetList: StateFlow<FilmPresentOnNetPlatform> = _moveNetList.asStateFlow()
//    init {
//        getMoviesUrlFromNet( 93412)
//    }
    fun getMoviesUrlFromNet(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getWebViewUseCase.getMoreUrlFilmOnNet(filmId)

            }.fold(
                onSuccess = { _moveNetList.value = it },
                onFailure = { Log.d("GetFilmNet", it.message ?: "") }
            )
        }
    }
}