package home.product.skillcinema2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import home.product.common.navigation.NavigationItem
import home.product.feature_api.register
import home.product.home_impl.presentation.HomeScreen
import home.product.home_api.HomeFeatureApi
import home.product.home_impl.navigation.HomeFeatureImpl
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
//        register(
//            featureApi = HomeFeatureImpl(),
//            navController = navController,
//            modifier = modifier
//        )
        composable(
            route =NavigationItem.HomeScreen.route
        )
        {
            HomeScreen(
                modifier = modifier,
               onNavigateTo={ navController.navigate(route = NavigationItem.HomeScreen.route)},
            )
        }
        composable(
            route =NavigationItem.SearchScreen.route
        )
        {
            SearchScreen(
                modifier = modifier,
                onNavigateTo={ navController.navigate(route = NavigationItem.SearchScreen.route)},
            )
        }
        composable(
            route =NavigationItem.ProfileScreen.route
        )
        {
            ProfileScreen(
                modifier = modifier,
                onNavigateTo={ navController.navigate(route = NavigationItem.ProfileScreen.route)},
            )
        }

    }
}