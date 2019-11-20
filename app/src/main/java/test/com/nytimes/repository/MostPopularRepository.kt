package test.com.nytimes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import test.com.nytimes.data.cache.CacheManager
import test.com.nytimes.data.remote.NYTimesService
import test.com.nytimes.data.remote.Resource
import test.com.nytimes.model.MostPopularResponse
import test.com.nytimes.model.PopularListLiveData


// Repository has inbuild in-memory cache
// First check if the response is already available in the memory, if 'Yes' , retrieve
// the response from memory and avoid network call. We can use 'Room' too for in-memory cache
class MostPopularRepository(private val service: NYTimesService) {

    fun getMostPopularList(section: String, period: Int, apiKey: String): LiveData<Resource<MostPopularResponse>> {

        if (!CacheManager.isEmpty()) {
            return buildCachedLiveData(CacheManager.loadMostPopular())
        }
            return PopularListLiveData(service.getMostPopular(section, period, apiKey))
    }


    private fun buildCachedLiveData(mostPopularResponse: MostPopularResponse): LiveData<Resource<MostPopularResponse>>{
        var mutableLiveData= MutableLiveData<Resource<MostPopularResponse>>()
        mutableLiveData.value = Resource.success(mostPopularResponse)

        return mutableLiveData
    }
}