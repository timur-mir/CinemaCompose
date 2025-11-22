package home.product.search_impl.presentation.screen

import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.util.LinkifyCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.presentation.viewmodel.FilmDetailViewModel
import home.product.search_impl.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.async

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AboutFilmScreen(modifier: Modifier, idMovie: Int,onNavigateToWebScreenWithArgument : (Int?) -> Unit, searchViewModel: SearchViewModel= hiltViewModel()){
    val coroutineScope = rememberCoroutineScope()
    val filmAboutInfo: FilmDetailInfo by searchViewModel.filmAboutInfo.collectAsState()
    val state = rememberScrollState()
    LaunchedEffect(Unit) {
        state.animateScrollTo(100)
        try {
            val data = coroutineScope.async {
                searchViewModel.getFilmAboutInfo(idMovie)
            }
            data.await()
        } catch (_: Exception) {
        }
    }
    Surface(color = Color(248, 64, 175, 100)) {

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(state),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(62.dp))
            Text(
                text = filmAboutInfo.nameRu?:filmAboutInfo.nameOriginal?:"",
                modifier = Modifier.padding(4.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )
            GlideImage(
                model = filmAboutInfo.posterUrlPreview,
                contentDescription = null,
                modifier = modifier
                    .height(520.dp)
                    .width(400.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(0.dp)
                    .clickable(onClick = { onNavigateToWebScreenWithArgument(idMovie)}),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.None
            )
            Spacer(modifier = Modifier.height(25.dp))
            if (filmAboutInfo.description != null) {
                Text(
                    text = filmAboutInfo.description?:"",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(25.dp))
              DefaultLinkifyText(
                    modifier = modifier,
                    filmAboutInfo.webUrl
                )

            } else {
              DefaultLinkifyText(
                    modifier = modifier,
                    filmAboutInfo.webUrl
                )
            }
        }
    }
}
@Composable
fun DefaultLinkifyText(modifier: Modifier = Modifier, text: String?) {
    val context = LocalContext.current
    val customLinkifyTextView = remember {
        TextView(context)
    }
    AndroidView(modifier = modifier, factory = { customLinkifyTextView }) { textView ->
        textView.text = text ?: ""
        LinkifyCompat.addLinks(textView, Linkify.WEB_URLS)
//        Linkify.addLinks(textView, Patterns.PHONE,"tel:",
//            Linkify.sPhoneNumberMatchFilter, Linkify.sPhoneNumberTransformFilter)
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}