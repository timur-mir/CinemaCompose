package home.product.home_api

import home.product.feature_api.FeatureApi

interface HomeFeatureApi: FeatureApi {
    val homeRoute: String
}