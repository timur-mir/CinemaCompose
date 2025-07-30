package home.product.home_impl.data.mapper

import home.product.home_impl.data.remote.response.PremieresList

fun PremieresList.toDomainPremieresList():home.product.home_impl.domain.model.response.PremieresList {
return home.product.home_impl.domain.model.response.PremieresList(this.items)
}