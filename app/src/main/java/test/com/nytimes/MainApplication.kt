package test.com.nytimes

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import test.com.nytimes.di.applicationModule


const val MOST_POPULAR_ITEM = "MOST_POPULAR_ITEM"
const val API_KEY = "sk7arVd8ySuyg708d9GqHDzlwTCkPoqA"

class MainApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(applicationModule)
        import(androidModule(this@MainApplication))
    }
}