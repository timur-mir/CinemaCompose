package home.product.search_impl.data.mapper

import home.product.search_impl.data.remote.response.Films
import home.product.search_impl.data.remote.response.FilmsList
//
//fun FilmsList.toDomainFilmsList()= home.product.search_impl.domen.model.response.FilmsList(this.films)
fun Films.toDomainFilm():home.product.search_impl.domen.model.response.Films {
    return home.product.search_impl.domen.model.response.Films(this.filmId,this.nameRu,this.nameEn,this.type,
    this.year,this.description,this.filmLength,this.countries,this.genres,this.rating)
}