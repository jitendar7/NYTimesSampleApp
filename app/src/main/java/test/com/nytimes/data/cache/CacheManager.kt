package test.com.nytimes.data.cache

import test.com.nytimes.model.MostPopularResponse

object CacheManager {
    private var mostPopularResponse: MostPopularResponse? = null

    fun save(mostPopularResponse: MostPopularResponse) {
        this.mostPopularResponse = mostPopularResponse
    }

    fun loadMostPopular(): MostPopularResponse {
        return this.mostPopularResponse!!
    }

    fun isEmpty(): Boolean {
        return mostPopularResponse == null
    }

}