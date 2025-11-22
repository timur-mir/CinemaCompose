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
    object WebViewScreen : NavigationItem(
        route = "web_screen",
        title = "WebScreen",
        icon = R.drawable.global
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
    object DetailMovieScreen: NavigationItem(
        route = "detail_movie_screen",
        title = "DetailMovie"
    )
//    object AboutFilmScreen: NavigationItem(
//        route = "about_film_screen",
//        title = "FilmDetail"
//    )
    object TestScreen: NavigationItem(
        route = "test_screen",
        title = "Test"
    )
}