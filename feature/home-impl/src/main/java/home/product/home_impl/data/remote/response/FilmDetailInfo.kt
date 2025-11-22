package home.product.home_impl.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class FilmDetailInfo(
    @SerializedName("kinopoiskId")
    val kinopoiskId: Int?,
    @SerializedName("imdbId")
    val imdbId:String?,
    @SerializedName("nameRu")
    val nameRu: String?,
    @SerializedName("nameOriginal")
    val nameOriginal:String?,
    @SerializedName("year")
    val year:Int?,
    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String?,
    @SerializedName("genres")
    val genres: ArrayList<home.product.home_impl.data.remote.response.Genre>?,
    @SerializedName("ratingImdb")
    val ratingImdb:String?,
    @SerializedName("filmLength")
    val filmLength:Int?,
    @SerializedName("ratingAgeLimits")
    val ratingAgeLimits:String?,
    @SerializedName("webUrl")
    val webUrl:String?,
    @SerializedName("serial")
    val serial:Boolean,
    @SerializedName("countries")
    val countries:ArrayList<Country>?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("type")
    val type:String?
)