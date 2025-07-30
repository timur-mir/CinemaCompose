package home.product.home_impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import home.product.home_impl.domain.model.response.PremieresList
import home.product.home_impl.presentation.InnerHelper.listPremieres
import home.product.home_impl.presentation.viewmodel.MovieViewModel


@Composable
fun HomeScreen(
    modifier: Modifier,
    onNavigateTo: () -> Unit,
    movieViewModel: MovieViewModel  = hiltViewModel()
) {
    //movieViewModel.loadPremieres()
    LaunchedEffect(key1 = true) {
       movieViewModel.premieresInfo.collect{list->
           listPremieres=list
       }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            listPremieres.toString(),
            modifier = Modifier.padding(36.dp),
            fontSize = 10.sp
        )
    }

}
object InnerHelper {
    var listPremieres= PremieresList(emptyList())
}