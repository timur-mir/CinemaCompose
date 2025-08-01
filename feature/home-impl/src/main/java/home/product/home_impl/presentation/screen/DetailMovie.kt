package home.product.home_impl.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import home.product.home_impl.presentation.viewmodel.MovieViewModel

@Composable
fun DetailMovie(
    modifier: Modifier,
    onNavigateTo: () -> Unit,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

}