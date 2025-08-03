package home.product.skillcinema2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import home.product.common.navigation.NavigationItem
import home.product.feature_api.register
import home.product.home_impl.presentation.HomeScreen
import home.product.home_api.HomeFeatureApi
import home.product.home_impl.navigation.HomeFeatureImpl
import home.product.home_impl.presentation.screen.DetailMovieScreen
import home.product.home_impl.presentation.screen.WebViewScreen
import home.product.profile_impl.presentation.ProfileScreen
import home.product.search_impl.navigation.presentation.SearchScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = NavigationItem.HomeScreen.route
    ) {

        composable(
            route = NavigationItem.HomeScreen.route
        )
        {
            HomeScreen(
                modifier = modifier,
                onNavigateTo = { navController.navigate(it) }
            )
        }
        composable(
            route = NavigationItem.DetailMovieScreen.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
            })
        )
        {
            it.arguments?.getInt("movieId")?.let { data ->
                DetailMovieScreen(
                    modifier = modifier,
                    idMovie = data,
                    onNavigateTo = { navController.navigate(it) })
            }

        }
        composable(
            route = NavigationItem.WebViewScreen.route+"/{movieWebId}",
            arguments = listOf(navArgument("movieWebId") {
                type = NavType.IntType
            })
        )
        { it ->
            it.arguments?.getInt("movieWebId")?.let { data ->
                WebViewScreen(
                    onNavigateTo = { navController.navigate(it) },
                    kinopoiskId = data
                )
            }
        }

        composable(
            route = NavigationItem.SearchScreen.route
        )
        {
            SearchScreen(
                modifier = modifier,
                onNavigateTo = { navController.navigate(route = NavigationItem.SearchScreen.route) },
            )
        }
        composable(
            route = NavigationItem.ProfileScreen.route
        )
        {
            ProfileScreen(
                modifier = modifier,
                onNavigateTo = { navController.navigate(route = NavigationItem.ProfileScreen.route) },
            )
        }


    }
}


//        register(
//            featureApi = HomeFeatureImpl(),
//            navController = navController,
//            modifier = modifier
//        )

//                backStackEntry ->
//            val arguments = requireNotNull(backStackEntry.arguments)
//            val argument = arguments.getString("urlMovie")
//
//            if (argument != null) {
//                DetailMovieScreen(
//                    modifier = modifier,
//                   url = argument
//                )
//            }