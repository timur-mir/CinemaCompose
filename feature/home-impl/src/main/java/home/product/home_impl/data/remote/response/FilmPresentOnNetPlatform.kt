package home.product.home_impl.data.remote.response

import com.google.gson.annotations.SerializedName

data class FilmPresentOnNetPlatform(
   // @SerializedName("total")
    var total: Int? = null,
  //  @SerializedName("items")
    var items: ArrayList<FilmNetSource> = arrayListOf()
)

data class FilmNetSource(
    var url: String? = null,
    var name: String? = null,
    var site: String? = null
)