package home.product.search_impl.domen.model.response

import home.product.search_impl.data.remote.response.Countries
import home.product.search_impl.data.remote.response.Genres

data class Films(
     var filmId: Int?                 = null,
     var nameRu: String?              = null,
     var nameEn: String?              = null,
     var type: String?              = null,
     var year: String?              = null,
     var description: String?              = null,
     var filmLength: String?              = null,
     var countries: ArrayList<Countries> = arrayListOf(),
     var genres: ArrayList<Genres> = arrayListOf(),
     var rating: String?              = null,
     var ratingVoteCount: Int?                 = null,
     var posterUrl: String?              = null,
     var posterUrlPreview: String?              = null

)
data class Genres(
    val genre: String?
)
data class Countries(
    val country: String?
)