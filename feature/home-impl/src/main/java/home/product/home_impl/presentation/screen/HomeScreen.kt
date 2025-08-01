package home.product.home_impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.presentation.screen.GlideImageWithPreview

import home.product.home_impl.presentation.viewmodel.MovieViewModel

import kotlinx.coroutines.async


@Composable
fun HomeScreen(
    modifier: Modifier,
    onNavigateTo: () -> Unit,
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    //val premieresList = remember { mutableStateOf<PremieresList>(PremieresList(emptyList())) }
    val premieresList: PremieresList by movieViewModel.premieresInfo.collectAsState()


    LaunchedEffect(Unit) {
//       movieViewModel.premieresInfo.collect{list->
//           listPremieres=list
//       }
        try {
            val data = coroutineScope.async {
                movieViewModel.loadPremieres()
            }
            data
                .await()

        } catch (e: Exception) {
        }
    }
    Surface(color = Color.Transparent) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            LazyRow(modifier=Modifier.padding(0.dp,30.dp,0.dp,0.dp),
                contentPadding = PaddingValues(10.dp)
            ){
                items(premieresList.items.size) { index ->
                    MovieItem(premieresList.items[index]) {}
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
//            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(modifier=Modifier.padding(0.dp,20.dp,0.dp,0.dp),
                contentPadding = PaddingValues(10.dp),
            reverseLayout = true){
                items(premieresList.items.size) { index ->
                    MovieItem(premieresList.items[index]) {}
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

        }
    }
}

@Composable
fun MovieItem(movie: home.product.home_impl.data.remote.response.Movie, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(6.dp),
        colors =CardDefaults.cardColors(containerColor = Color(10,30,50,100), contentColor = Color.White),
        modifier = Modifier
            .height(220.dp)
            .width(130.dp)
    ) {
        Row(
            modifier = Modifier
                .height(220.dp)
                .width(130.dp)
                .clickable(onClick = onClick)
                .padding(2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
            ) {
                GlideImageWithPreview(
                    data = movie.posterUrl ?: movie.posterUrlPreview
                    ?: "${home.product.common.R.drawable.fotosimple}",
                    Modifier
                        .height(156.dp)
                        .width(130.dp)

                )
                Text(
                    text = movie.nameRu ?: "Фильм",
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = "${movie.genres?.get(0)?.genre}",
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}



