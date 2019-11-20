package test.com.nytimes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import test.com.nytimes.model.MostPopularParameters
import test.com.nytimes.model.MostPopularResponse
import test.com.nytimes.data.remote.Resource
import test.com.nytimes.repository.MostPopularRepository

class MostPopularViewModel(mostPopularRepository: MostPopularRepository) : ViewModel() {

    private val _parameters: MutableLiveData<MostPopularParameters> = MutableLiveData()
    val parameters: LiveData<MostPopularParameters>
        get() = _parameters


    private val _hideProgress: MutableLiveData<Boolean> = MutableLiveData()
    val hideProgress: LiveData<Boolean>
        get() = _hideProgress


    val mostPopular: LiveData<Resource<MostPopularResponse>> = Transformations.switchMap(_parameters) {
        mostPopularRepository.getMostPopularList(it.section, it.period, it.apiKey)
    }

    fun initialize(mostPopularParameters: MostPopularParameters) {
        _parameters.value = mostPopularParameters
    }

    fun setProgressValue(bool: Boolean) {
        _hideProgress.value = bool
    }

}