package home.product.home_impl.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import home.product.common.database.PremieresEntity
import home.product.common.database.PremieresRepository
import home.product.home_impl.data.mapper.toDomainPremieresList

import home.product.home_impl.domain.model.response.Country
import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform
import home.product.home_impl.domain.model.response.Genre
import home.product.home_impl.domain.model.response.Movie
import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.domain.usecase.FilmDetailInfoUseCase
import home.product.home_impl.domain.usecase.Premieres2UseCase
import home.product.home_impl.domain.usecase.Premieres3UseCase
import home.product.home_impl.domain.usecase.PremieresLoadFromDaoUseCase
import home.product.home_impl.domain.usecase.PremieresSaveToDaoUseCase
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
    private val getPremieres2UseCase: Premieres2UseCase,
    private val getPremieres3UseCase: Premieres3UseCase,
    private val getSaverFunction:PremieresSaveToDaoUseCase,
    private val getLoaderPremieres:PremieresLoadFromDaoUseCase
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

    private val _premieresInfo4 = MutableStateFlow<PremieresList>(PremieresList(emptyList()))
    val premieresInfo4: StateFlow<PremieresList> = _premieresInfo4.asStateFlow()

    suspend fun savePremieresToDao(list:List<PremieresEntity>) {
        getSaverFunction.savePremieresDao(list)
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
                    _premieresInfo.value =
                        getLoaderPremieres.getPremieresDao().toDomainPremieresList()
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
                getPremieres2UseCase.getPremieres(2025, "september")
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
                getPremieres3UseCase.getPremieres(2025, "october")
            }.fold(
                onSuccess = { response ->
                    _premieresInfo3.value = response
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

    fun loadPremieres4() {
        downloader ?: viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                getPremieresUseCase.getPremieres(2025, "november")
            }.fold(
                onSuccess = { response ->
                    _premieresInfo4.value = response
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