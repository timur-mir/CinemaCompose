package home.product.home_impl.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TestScreen( modifier: Modifier){
    Text(
        "работает",
        modifier = Modifier.padding(16.dp),
        fontSize = 24.sp
    )
}