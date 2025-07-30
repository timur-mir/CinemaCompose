package home.product.home_impl.domain.model.response

import home.product.home_impl.data.remote.response.Movie

data class PremieresList(
    val items: List<Movie>
)