package test.com.nytimes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import test.com.nytimes.model.MostPopularParameters
import test.com.nytimes.repository.MostPopularRepository
import test.com.nytimes.model.MostPopularResponse
import test.com.nytimes.remote.Resource

class MostPopularViewModel(mostPopularRepository: MostPopularRepository) : ViewModel() {

    val parameters: MutableLiveData<MostPopularParameters> = MutableLiveData()
    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()


    val mostPopular: LiveData<Resource<MostPopularResponse>> = Transformations.switchMap(parameters) {
        mostPopularRepository.getMostPopularList(it.section, it.period, it.apiKey)
    }

}