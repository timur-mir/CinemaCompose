package home.product.search_api

import home.product.feature_api.FeatureApi

interface SearchFeatureApi: FeatureApi {
    val searchRoute: String
}