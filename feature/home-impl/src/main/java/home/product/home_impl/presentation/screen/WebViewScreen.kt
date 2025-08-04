package home.product.home_impl.presentation.screen

import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform
import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.presentation.screen.UrlEx.urlWeb
import home.product.home_impl.presentation.viewmodel.GetNetUrlViewModel
import home.product.home_impl.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WebViewScreen(
    kinopoiskId: Int,
    onNavigateTo: (String) -> Unit,
    getNetUrlViewModel: GetNetUrlViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val moviesFromNet: FilmPresentOnNetPlatform by getNetUrlViewModel.moveNetList.collectAsState()
    // val urlMain = remember { mutableStateOf("https://www.google.com") }
    LaunchedEffect(Unit) {
        try {
            val data = coroutineScope.async(Dispatchers.IO) {
                getNetUrlViewModel.getMoviesUrlFromNet(kinopoiskId)
            }
            data
                .await()
            delay(200)
            getNetUrlViewModel.moveNetList.collect {
                urlWeb = it.items[1].url.toString()
            }
        } catch (_: Exception) {
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    clearCache(true)
                    settings.cacheMode = WebSettings.LOAD_DEFAULT
                    settings.setSupportZoom(true)
                    settings.builtInZoomControls = true
                    settings.displayZoomControls = true
                    settings.textZoom = 100
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    fitsSystemWindows = true
                    setLayerType(View.LAYER_TYPE_HARDWARE, null)
                }
            },
            update = { webView ->
                if (urlWeb.isNotEmpty() && urlWeb != "null") {
                    webView.loadUrl(moviesFromNet.items[0].url.toString())
                } else {
                    webView.loadUrl("https://kinopoiskapiunofficial.tech/documentation/api/#/films/get_api_v2_2_films__id__external_sources")
                    //Ограничение на запрос...Code - 429...???
                }
            }
        )
    }
}
object UrlEx {
    var urlWeb = ""
}