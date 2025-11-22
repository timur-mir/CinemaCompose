package home.product.home_impl.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.navigation
import home.product.common.navigation.NavigationItem
import home.product.home_api.HomeFeatureApi
import home.product.home_impl.presentation.HomeScreen
import home.product.home_impl.presentation.screen.DetailMovieScreen
import home.product.home_impl.presentation.screen.WebViewScreen

const val baseRoute = "home"
const val scenarioDetailWebRoute = "$baseRoute/scenario"
const val screenDetail = "$scenarioDetailWebRoute /D"
val screenWeb = "$scenarioDetailWebRoute /W"
private const val argumentKey = "arg"

class HomeFeatureImpl : HomeFeatureApi {

    override val homeRoute = baseRoute

    //    @RequiresApi(Build.VERSION_CODES.O)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            baseRoute
        )
        {
            HomeScreen(
                onNavigateTo = { movieId ->
                    navController.navigate("${screenDetail}/$movieId") {
//                        popUpTo(baseRoute) { inclusive = true }
                    }
                }
            )


        }


        navGraphBuilder.composable(
            route = "$screenDetail/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
            })
        )
        {
            it.arguments?.getInt("movieId")?.let { data ->
                DetailMovieScreen(
                    modifier = modifier,
                    idMovie = data,
                    onNavigateTo = { navController.navigate("$screenWeb/$data") })
            }

        }
        navGraphBuilder.composable(
            route = "$screenWeb/{movieWebId}",
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

    }

//        navGraphBuilder.composable(
//         baseRoute
//        )
//        {
//            HomeScreen(
//                onNavigateTo = { movieId ->
//                    navController.navigate("$scenarioDetailWebRoute/$movieId") {
//                        popUpTo(baseRoute) { inclusive = true }
//                    }
//                }
//            )
//
//
//        }
//           navGraphBuilder.navigation(
//                route = "$scenarioDetailWebRoute/{movieId}",
//                startDestination = screenDetail
//            )
//            {
//                composable(
//                    route = "$screenDetail/{movieId}",
//                    arguments = listOf(navArgument("movieId") {
//                        type = NavType.IntType
//                    })
//                )
//                {
//                    it.arguments?.getInt("movieId")?.let { data ->
//                        DetailMovieScreen(
//                            modifier = modifier,
//                            idMovie = data,
//                            onNavigateTo = { navController.navigate("$screenWeb/$data") })
//                    }
//
//                }
//                composable(
//                    route = "$screenWeb/{movieWebId}",
//                    arguments = listOf(navArgument("movieWebId") {
//                        type = NavType.IntType
//                    })
//                )
//                { it ->
//                    it.arguments?.getInt("movieWebId")?.let { data ->
//                        WebViewScreen(
//                            onNavigateTo = { navController.navigate(it) },
//                            kinopoiskId = data
//                        )
//                    }
//                }
//
//            }
}
//}
