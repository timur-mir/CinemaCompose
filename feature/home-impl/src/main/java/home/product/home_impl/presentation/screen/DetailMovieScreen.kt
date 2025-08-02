package home.product.home_impl.presentation.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import home.product.common.navigation.NavigationItem
import home.product.home_impl.domain.model.response.FilmDetailInfo
import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailMovieScreen(
    modifier: Modifier, idMovie: Int, onNavigateTo: (String) -> Unit, movieViewModel: MovieViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val filmDetailInfo: FilmDetailInfo by movieViewModel.filmDetailInfo.collectAsState()
    val state = rememberScrollState()
    LaunchedEffect(Unit) {
        state.animateScrollTo(100)
        try {
            val data = coroutineScope.async {
                movieViewModel.getFilmDetailInfo(idMovie)
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
                .clickable(onClick = {onNavigateTo(NavigationItem.WebViewScreen.route + "/${filmDetailInfo.kinopoiskId}") })
                .verticalScroll(state)
                .clip(shape = RoundedCornerShape(16.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(62.dp))
            Text(
                text = filmDetailInfo.nameRu.toString(),
                modifier = Modifier.padding(4.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )
            GlideImage(
                model = filmDetailInfo.posterUrlPreview,
                contentDescription = null,
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = filmDetailInfo.description.toString(),
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }

}