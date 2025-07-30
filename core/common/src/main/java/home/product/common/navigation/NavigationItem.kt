package home.product.common.navigation

import home.product.common.R

sealed class NavigationItem  (
    val route : String,
    val title : String,
    val icon : Int = R.drawable.ic_home,
    val navBack : String = ""
        ) {
    object HomeScreen : NavigationItem(
        "home_screen",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object SearchScreen : NavigationItem(
        route = "search_screen",
        title = "Search",
        icon = R.drawable.search
    )
    object ProfileScreen : NavigationItem(
        route = "profile_screen",
        title = "Profile",
        icon = R.drawable.profile
    )
}