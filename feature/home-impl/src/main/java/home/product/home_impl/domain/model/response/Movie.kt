package home.product.home_impl.domain.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val kinopoiskId: Int,
    var nameRu: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val genres: ArrayList<Genre>?,
    val premiereRu: String?,
    val countries: ArrayList<Country>?,
    val rating: String?,
    var viewed: Boolean =false,
    val filmId:Int

): Parcelable
@Parcelize
data class Genre(
    val genre: String?
): Parcelable

@Parcelize
data class Country(
    val country: String?
): Parcelable