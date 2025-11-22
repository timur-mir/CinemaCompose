package home.product.search_impl.navigation

import android.net.Uri
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.navigation
import home.product.common.navigation.NavigationItem
import home.product.search_api.SearchFeatureApi
import home.product.search_impl.navigation.presentation.SearchScreen
import home.product.search_impl.presentation.screen.AboutFilmScreen
import home.product.search_impl.presentation.screen.WebViewScreenS


const val baseRoute = "search"
class SearchFeatureImpl : SearchFeatureApi {
    override val searchRoute: String = baseRoute
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            searchRoute
        )
        {
            SearchScreen(
                modifier = modifier,
                onNavigateToAboutScreenWithArgument = { idFilm ->
                    navController.navigate("nested/$idFilm") {
                        popUpTo(
                            route = "search"
                        )
                        { inclusive = false}
                    }
                }
            )

        }
        navGraphBuilder.navigation(
            route = "nested/{data}",
            startDestination = "nested/aboutfilmscreen",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.IntType
                }
            )
        ) {
            composable(
                route = "nested/aboutfilmscreen"
            ) { backStackEntry ->
                 val param1Value = backStackEntry.arguments?.getInt("data")
                if (param1Value != null) {
                AboutFilmScreen(
                    modifier = modifier,
                    idMovie = param1Value,
                    onNavigateToWebScreenWithArgument = { idFilmR ->
                        navController.navigate("nested/webviewscreens/$idFilmR") {
                       }
                    }
                )
                 }
            }
            composable(
                route = "nested/webviewscreens/{data2}",
                        arguments = listOf(
                        navArgument("data2") {
                            type = NavType.IntType
                        }
                        )
            ) {backStackEntry->
                val paramRepeatValue = backStackEntry.arguments?.getInt("data2")
                if (paramRepeatValue != null) {
                    WebViewScreenS(paramRepeatValue)
                }
            }
        }
    }
}





