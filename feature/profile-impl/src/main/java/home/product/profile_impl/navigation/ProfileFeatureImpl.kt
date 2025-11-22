package home.product.profile_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import home.product.profile_api.ProfileFeatureApi
import home.product.profile_impl.presentation.ProfileScreen

const val baseRoute = "profile"
class ProfileFeatureImpl :ProfileFeatureApi {
    override val profileRoute: String="$baseRoute"
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = profileRoute
        )
        {
            ProfileScreen(
                modifier = modifier,
                onNavigateTo = { navController.navigate( route = profileRoute) },
            )
        }
    }
}