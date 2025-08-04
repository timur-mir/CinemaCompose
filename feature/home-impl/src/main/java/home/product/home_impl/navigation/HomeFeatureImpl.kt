package home.product.home_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import home.product.home_api.HomeFeatureApi
import home.product.home_impl.presentation.HomeScreen

private const val baseRoute = "home"
class HomeFeatureImpl : HomeFeatureApi {
    override val homeRoute = baseRoute
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(baseRoute) {
            HomeScreen(
                onNavigateTo = {
                    navController.navigate("anyScreen")
                }
            )
        }
    }
}