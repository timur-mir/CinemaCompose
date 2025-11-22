package home.product.home_impl.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Movie(
    @SerializedName("kinopoiskId")
    val kinopoiskId: Int,
    @SerializedName("nameRu")
    var nameRu: String?,
    @SerializedName("posterUrl")
    val posterUrl: String?,
    @SerializedName("PosterUrlPreviews")
    val posterUrlPreview: String?,
    @SerializedName("genres")
    val genres: ArrayList<Genre>?,
    @SerializedName("premieresRu")
    val premiereRu: String?,
    @SerializedName("countries")
    val countries: ArrayList<Country>?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("viewed")
    var viewed: Boolean =false,
    @SerializedName("filmId")
    val filmId:Int
)
data class Genre(
    @SerializedName("genre")
    val genre: String?
)

data class Country(
    @SerializedName("country")
    val country: String?
)