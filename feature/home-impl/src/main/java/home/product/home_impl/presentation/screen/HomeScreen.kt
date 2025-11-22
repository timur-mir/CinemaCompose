package home.product.home_impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import home.product.common.database.PremieresEntity
import home.product.common.navigation.NavigationItem

import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.presentation.screen.GlideImageWithPreview

import home.product.home_impl.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onNavigateTo: (Int) -> Unit,
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val premieresList: PremieresList by movieViewModel.premieresInfo.collectAsState()
    val premieresList2: PremieresList by movieViewModel.premieresInfo2.collectAsState()
    val premieresList3: PremieresList by movieViewModel.premieresInfo3.collectAsState()
    val state = rememberScrollState()
    var premieresListForDao: MutableList<PremieresEntity> = mutableListOf()

    val baseRoute = "home"
    val scenarioDetailWebRoute = "$baseRoute/scenario"
    val screenDetail = "$scenarioDetailWebRoute /Detail"

    LaunchedEffect(Unit) {
        state.animateScrollTo(100)
        try {
            val data = coroutineScope.async {
                coroutineScope.launch(Dispatchers.IO) {  movieViewModel.loadPremieres() }
                coroutineScope.launch(Dispatchers.IO) {  movieViewModel.loadPremieres2() }
                coroutineScope.launch(Dispatchers.IO) {  movieViewModel.loadPremieres3() }
            }
            data
                .await()
        } catch (_: Exception) {
        }
    }
    Surface(color = Color.Transparent) {
        Column(
            modifier = Modifier
                .verticalScroll(state),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            LazyRow(
                modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
                contentPadding = PaddingValues(2.dp)
            ) {
                items(premieresList.items.size) { index ->
                    MovieItem(premieresList.items[index]) { id ->
                        onNavigateTo(id)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                coroutineScope.launch {

                    if (premieresList.items.isNotEmpty()) {
                        for (item in premieresList.items) {
                            premieresListForDao.add(
                                PremieresEntity(
                                    item.kinopoiskId,
                                    item.nameRu,
                                    item.posterUrl,
                                    item.posterUrlPreview,
                                    item.genres?.joinToString(",") { it.genre.toString() }
                                        .toString(),
                                    item.premiereRu,
                                    item.countries?.joinToString(",") { it.country.toString() }
                                        .toString(),
                                    item.rating.toString(),
                                    item.viewed,
                                    item.filmId
                                )
                            )
                        }
                        movieViewModel.savePremieresToDao(premieresListForDao)
                    }

                }
            }

            LazyRow(
                modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
                contentPadding = PaddingValues(2.dp)
            ) {
                items(premieresList2.items.size) { index ->
                    MovieItem(premieresList2.items[index]) { id ->
                        onNavigateTo(id)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            LazyRow(
                modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
                contentPadding = PaddingValues(2.dp)
            ) {
                items(premieresList3.items.size) { index ->
                    MovieItem(premieresList3.items[index]) { id ->
                        onNavigateTo(id)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(160.dp))
        }
    }
}

@Composable
fun MovieItem(movie: home.product.home_impl.data.remote.response.Movie, onClick: (Int) -> Unit) {
    Card(
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(10, 30, 50, 100),
            contentColor = Color.White
        ),
        modifier = Modifier
            .padding(0.dp, 4.dp, 0.dp, 0.dp)
            .height(220.dp)
            .width(130.dp)
            .clickable(onClick = { onClick(movie.kinopoiskId) })
    ) {

        Column(
            modifier = Modifier
                .height(220.dp)
                .width(130.dp)
                .padding(6.dp, 6.dp, 6.dp, 2.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top

        ) {
            GlideImageWithPreview(
                data = movie.posterUrlPreview ?: movie.posterUrl
                ?: "${home.product.common.R.drawable.fotosimple}",
                Modifier
                    .height(156.dp)
                    .width(130.dp)
            )
            Text(
                modifier = Modifier
                    .padding(4.dp, 2.dp, 2.dp, 0.dp),
                text = movie.nameRu ?: "Фильм",
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.sp,
                    color = Color.White
                ),
                maxLines = 2
            )
            Text(
                modifier = Modifier
                    .padding(4.dp, 2.dp, 2.dp, 0.dp),
                text = "${movie.genres?.get(0)?.genre}",
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 10.sp,
                    color = Color.White
                ),
                maxLines = 1
            )
        }
    }
}




