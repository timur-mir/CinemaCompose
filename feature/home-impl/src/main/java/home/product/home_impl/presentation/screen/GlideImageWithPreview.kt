package home.product.home_impl.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import home.product.home_impl.R


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImageWithPreview(
    data: Any?,
    modifier: Modifier? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    if (data == null)
        Image(
            painter = painterResource(id = home.product.common.R.drawable.fotosimple),
            contentDescription = contentDescription,
            modifier = modifier ?: Modifier,
            alignment = Alignment.Center,
            contentScale = contentScale
        )
    else {

        GlideImage(
            model = data,
            contentDescription = contentDescription,
            modifier = modifier ?: Modifier,
            contentScale = contentScale
        )
    }
}
//                Image(
//                    modifier = Modifier
//                        .clip(shape = CircleShape)
//                        .size(56.dp),
//                    painter = painterResource(id = home.product.common.R.drawable.fotosimple),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop
//                )