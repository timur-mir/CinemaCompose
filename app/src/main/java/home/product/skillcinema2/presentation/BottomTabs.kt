package home.product.skillcinema2.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import home.product.common.navigation.NavigationItem
import home.product.skillcinema2.R

enum class BottomTabs(
    @StringRes
    val title: Int,
    @DrawableRes
    val icon: Int,
    val route: String
) {

    HOME(
        R.string.home,
        home.product.common.R.drawable.baseline_home_24,
        "home"
    ),
    SEARCH(
        R.string.search,
        home.product.common.R.drawable.baseline_search_24,
        "search"
    ),
    Profile(
        R.string.profile,
        home.product.common.R.drawable.baseline_emoji_people_24,
        "profile"
    ),
}