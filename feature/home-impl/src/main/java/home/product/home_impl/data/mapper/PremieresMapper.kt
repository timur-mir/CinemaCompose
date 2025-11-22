package home.product.home_impl.data.mapper

import home.product.home_impl.data.remote.response.FilmDetailInfo
import home.product.home_impl.data.remote.response.FilmPresentOnNetPlatform
import home.product.home_impl.data.remote.response.PremieresList
import home.product.home_impl.domain.model.response.Country
import home.product.home_impl.domain.model.response.FilmNetSource
import home.product.home_impl.domain.model.response.Genre

fun PremieresList.toDomainPremieresList(): home.product.home_impl.domain.model.response.PremieresList {
    return home.product.home_impl.domain.model.response.PremieresList(this.items)
}

fun FilmPresentOnNetPlatform.toDomainFilmPresentOnNetPlatform(): home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform {
    return home.product.home_impl.domain.model.response.FilmPresentOnNetPlatform(
        total = this.total,
        items.map { it ->
            FilmNetSource(
                url = it.url,
                name = it.name,
                site = it.site
            )
        } as ArrayList<FilmNetSource>)
}
    fun FilmDetailInfo.toDomainFilmDetailInfo(): home.product.home_impl.domain.model.response.FilmDetailInfo {
        return home.product.home_impl.domain.model.response.FilmDetailInfo(
            kinopoiskId = this.kinopoiskId,
            imdbId = this.imdbId,
            nameRu = this.nameRu,
            nameOriginal = this.nameOriginal,
            year = this.year,
            posterUrlPreview = this.posterUrlPreview,
            genres = this.genres?.map { it->Genre(it.genre.toString())  } as ArrayList<home.product.home_impl.domain.model.response.Genre>,
            ratingImdb=this.ratingImdb,
            webUrl = this.webUrl,
            filmLength = this.filmLength,
            ratingAgeLimits = this.ratingAgeLimits,
            serial = this.serial,
            countries = this.countries?.map { it->Country(it.country) } as ArrayList<Country>,
            description = this.description,
            type = this.type
        )

}