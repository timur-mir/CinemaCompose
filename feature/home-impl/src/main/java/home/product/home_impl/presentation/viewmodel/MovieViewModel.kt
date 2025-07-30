package home.product.home_impl.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import home.product.home_impl.domain.model.response.Movie
import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.domain.usecase.PremieresUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPremieresUseCase: PremieresUseCase,
) : ViewModel() {
    private var downloader: Job? = null
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _premieresInfo = MutableStateFlow<PremieresList>(PremieresList(emptyList()))
    val premieresInfo = _premieresInfo.asStateFlow()

    init {
        loadPremieres()
    }
fun loadPremieres() {
        downloader ?: viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                getPremieresUseCase.getPremieres(2025, "august")
            }.fold(
                onSuccess = { response->
                   // _premieresInfo.value = it.filter { movie -> movie.countries?.any { it.country == "Россия" }?:false}
                   _premieresInfo.value =response
                    Log.d("TAG2", "${response}")
                },
                onFailure = {
                    Log.d("PremieresListViewModel", it.message ?: "")
                    // _premieresInfo.value= repository.getPremieresAtDao()
                }
            )
            _isLoading.value = false
            downloader?.cancel()
            downloader = null
        }
    }
}