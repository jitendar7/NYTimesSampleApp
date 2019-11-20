package test.com.nytimes.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import test.com.nytimes.repository.MostPopularRepository
import test.com.nytimes.data.remote.APIClient
import test.com.nytimes.data.remote.NYTimesService
import test.com.nytimes.viewmodel.ViewModelFactory


// Builds the application module:
// NYTimesService, MostPopularRepository, ViewModelFactory are added to access these through out the application
// Append the new modules as per the requirement

val applicationModule = Kodein.Module {

    bind<NYTimesService>() with singleton {
        APIClient.client.create(NYTimesService::class.java)
    }

    bind<MostPopularRepository>() with singleton { MostPopularRepository(instance()) }
    bind<ViewModelFactory>() with singleton { ViewModelFactory(instance()) }
}