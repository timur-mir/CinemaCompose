package home.product.home_impl.domain.model.response

import com.google.gson.annotations.SerializedName

data class FilmPresentOnNetPlatform(
    var total: Int? = null,
    var items: ArrayList<FilmNetSource> = arrayListOf()
)

data class FilmNetSource(
    var url: String? = null,
    var name: String? = null,
    var site: String? = null
)