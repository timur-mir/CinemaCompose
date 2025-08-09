package home.product.home_impl.domain.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmDetailInfo(
    val kinopoiskId: Int?,
    val imdbId:String?,
    val nameRu: String?,
    val nameOriginal:String?,
    val year:Int?,
    val posterUrlPreview: String?,
    val genres: ArrayList<Genre>?,
    val ratingImdb:String?,
    val webUrl:String?,
    val filmLength:Int?,
    val ratingAgeLimits:String?,
    val serial:Boolean,
    val countries:ArrayList<Country>?,
    val description:String?,
    val type:String?

): Parcelable