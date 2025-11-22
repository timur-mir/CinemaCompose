package home.product.skillcinema2.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import home.product.dependencyprovider.DependencyProvider
import home.product.home_impl.navigation.HomeFeatureImpl
import home.product.profile_api.ProfileFeatureApi
import home.product.profile_impl.navigation.ProfileFeatureImpl
import home.product.search_impl.navigation.SearchFeatureImpl
import home.product.skillcinema2.ui.theme.Skillcinema2Theme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        DependencyProvider.provideImpl(
            homeFeatureApi = HomeFeatureImpl(),
            searchFeatureApi= SearchFeatureImpl(),
           profileFeatureApi = ProfileFeatureImpl()
        )
        setContent {
            Skillcinema2Theme {
                AppContent()
            }
        }
    }
}
