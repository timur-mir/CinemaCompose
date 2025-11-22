package home.product.skillcinema2.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import home.product.skillcinema2.navigation.AppNavGraph
import home.product.skillcinema2.ui.theme.Skillcinema2Theme
import home.product.skillcinema2.ui.theme.backgroundWhite
import java.util.Locale


//@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    ProvideWindowInsets {
        Skillcinema2Theme{
            val tabs = remember { BottomTabs.values() }
            val navController = rememberNavController()
            Scaffold(
                topBar= {
                    TopAppBar(
                        title = { Text("Skillcinema2")},
                        navigationIcon={ IconButton({ }) { Icon(Icons.Filled.Menu, contentDescription = "Меню")}},
                        actions={
                            IconButton({ }) { Icon(Icons.Filled.Info, contentDescription = "О приложении")}
                        },
                        colors= TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = Color(7, 95, 95,100),
                                titleContentColor = Color.White,
                                navigationIconContentColor = Color.White,
                                actionIconContentColor = Color.White)
                        )

                },
                containerColor = Color(17, 238, 238,100),
                bottomBar = { BottomBar(navController = navController, tabs) }
            ) { innerPaddingModifier ->
                AppNavGraph(
                    navController = navController,
                    modifier = Modifier.padding(innerPaddingModifier)
                )
            }
        }
    }
}
@Composable
fun BottomBar(navController: NavController, tabs: Array<BottomTabs>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: BottomTabs.HOME.route
    val routes = remember { BottomTabs.values().map { it.route } }
    if (currentRoute in routes) {
        NavigationBar(
            Modifier.navigationBarsHeight(additional = 56.dp),
            containerColor = Color(7, 95, 95,100)
        ) {
            tabs.forEach { tab ->
                NavigationBarItem(
                    icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                    label = { Text(stringResource(tab.title).uppercase(Locale.getDefault())) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Gray,
                        unselectedIconColor = Color.White,
                        selectedTextColor = Color.Transparent ,
                        indicatorColor = Color(
                            16,
                            160,
                            160,
                            100
                        )
                    )
                )
            }
        }
    }
}
