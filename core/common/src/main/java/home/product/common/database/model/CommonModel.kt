package home.product.common.database.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("genre")
    val genre: String?
)

data class Country(
    @SerializedName("country")
    val country: String?
)