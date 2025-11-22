package home.product.search_impl.navigation.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import home.product.common.navigation.NavigationItem
import home.product.search_impl.R
import home.product.search_impl.data.remote.response.Films

import home.product.search_impl.domen.model.response.FilmsList
import home.product.search_impl.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    modifier: Modifier,
    onNavigateToAboutScreenWithArgument : (Int?) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val results  by searchViewModel.searchResults.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            textState,
            onSearch = { query ->
                if (query != "") {
                    searchViewModel.search(query)
                }
            }
        )
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            if (results.films.size > 0) {
                LazyColumn {
                    items(results.films) { result ->
                        FilmItem(result) { id ->
                           // onNavigateTo(NavigationItem.AboutFilmScreen.route + "/$id")
                            onNavigateToAboutScreenWithArgument(id)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = result.toString(),
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .fillMaxWidth()
//                    )
                    }
                }
            }
            else {
                                    Text(
                        text = "",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
            }
        }
    }
}

// Компонент SearchBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    state: MutableState<TextFieldValue>,
    onSearch: (String) -> Unit
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { valueS ->
            state.value = valueS
            onSearch(valueS.text.toString())
        },
        label = { Text("Поиск") },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Поиск",

            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}
@Composable
fun FilmItem(movie: Films, onClick: (Int) -> Unit) {
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
            .clickable { onClick(movie.filmId!!) }
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
                ?: (R.drawable.fotosimple),
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
                text = "${movie.genres.get(0).genre}",
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
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImageWithPreview(
    data: Any?,
    modifier: Modifier? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    if (data == null)
        Image(
            painter = painterResource(id = R.drawable.fotosimple),
            contentDescription = contentDescription,
            modifier = modifier ?: Modifier,
            alignment = Alignment.Center,
            contentScale = contentScale
        )
    else {

        GlideImage(
            model = data,
            contentDescription = contentDescription,
            modifier = modifier ?: Modifier,
            contentScale = contentScale
        )
    }
}
