package home.product.skillcinema2.navigation

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.navigation
import home.product.common.navigation.NavigationItem
import home.product.dependencyprovider.DependencyProvider
import home.product.feature_api.register
import home.product.home_impl.presentation.HomeScreen
import home.product.home_api.HomeFeatureApi
import home.product.home_impl.navigation.HomeFeatureImpl
import home.product.home_impl.presentation.screen.DetailMovieScreen
import home.product.home_impl.presentation.screen.WebViewScreen
import home.product.profile_impl.navigation.ProfileFeatureImpl
import home.product.profile_impl.presentation.ProfileScreen
import home.product.search_impl.navigation.SearchFeatureImpl
import home.product.search_impl.navigation.presentation.SearchScreen
import home.product.search_impl.presentation.screen.AboutFilmScreen

//@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination =DependencyProvider.homeFeature().homeRoute
    ) {
        register(
           DependencyProvider.homeFeature(),
            navController = navController,
            modifier = modifier
        )
        register(
           DependencyProvider.searchFeature(),
            navController = navController,
            modifier = modifier
        )
        register(
           DependencyProvider.profileFeature() ,
            navController = navController,
            modifier = modifier
        )
    }
}
