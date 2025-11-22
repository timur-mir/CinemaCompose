package home.product.dependencyprovider

import home.product.home_api.HomeFeatureApi
import home.product.profile_api.ProfileFeatureApi
import home.product.search_api.SearchFeatureApi

object DependencyProvider {
    private lateinit var homeFeatureApi: HomeFeatureApi
    private lateinit var searchFeatureApi: SearchFeatureApi
    private lateinit var profileFeatureApi: ProfileFeatureApi

    fun provideImpl(
        homeFeatureApi: HomeFeatureApi,
        searchFeatureApi: SearchFeatureApi,
        profileFeatureApi: ProfileFeatureApi
    ) {
        this.homeFeatureApi = homeFeatureApi
        this.searchFeatureApi = searchFeatureApi
        this.profileFeatureApi = profileFeatureApi
    }

    fun homeFeature(): HomeFeatureApi = homeFeatureApi

    fun searchFeature(): SearchFeatureApi= searchFeatureApi

    fun profileFeature(): ProfileFeatureApi = profileFeatureApi
}