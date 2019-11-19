package test.com.nytimes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.com.nytimes.repository.MostPopularRepository

class ViewModelFactory(private val mostPopularRepository: MostPopularRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MostPopularViewModel::class.java -> MostPopularViewModel(mostPopularRepository) as T
        else -> throw IllegalArgumentException("Unknow model class.")
    }

}