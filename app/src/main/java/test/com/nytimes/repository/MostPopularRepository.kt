package test.com.nytimes.repository

import androidx.lifecycle.LiveData
import test.com.nytimes.model.MostPopularResponse
import test.com.nytimes.model.PopularListLiveData
import test.com.nytimes.remote.NYTimesService
import test.com.nytimes.remote.Resource

class MostPopularRepository(private val service: NYTimesService) {

    fun getMostPopularList(section: String, period: Int, apiKey: String): LiveData<Resource<MostPopularResponse>> {
        val popularList = service.getMostPopular(section, period, apiKey)
        return PopularListLiveData(popularList)
    }
}