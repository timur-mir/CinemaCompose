package home.product.profile_api

import home.product.feature_api.FeatureApi

interface ProfileFeatureApi: FeatureApi {
    val profileRoute: String
}