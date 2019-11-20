package test.com.nytimes.model

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.com.nytimes.data.cache.CacheManager
import test.com.nytimes.data.remote.Resource

class PopularListLiveData<MostPopularResponse>(private val mostPopular: Call<MostPopularResponse>) :
        LiveData<Resource<MostPopularResponse>>() {

    init {
        value = Resource.loading()
    }

    override fun onActive() {
        super.onActive()

        if (!mostPopular.isExecuted) {
            mostPopular.enqueue(object : Callback<MostPopularResponse> {
                override fun onFailure(call: Call<MostPopularResponse>?, t: Throwable?) {
                    value = Resource.error(t)
                }

                override fun onResponse(call: Call<MostPopularResponse>?, response: Response<MostPopularResponse>?) {
                    if (response != null) {
                        CacheManager.save(response.body() as test.com.nytimes.model.MostPopularResponse)
                        value = Resource.success(response.body())
                    } else
                        value = Resource.error(Exception("null data"))
                }
            })
        }

    }

    override fun onInactive() {
        super.onInactive()
        mostPopular.cancel()
    }

}