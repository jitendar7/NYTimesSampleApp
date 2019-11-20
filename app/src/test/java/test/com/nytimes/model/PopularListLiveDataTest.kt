package test.com.nytimes.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.com.nytimes.data.remote.NYTimesService

class PopularListLiveDataTest{
    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private var mockWebServer = MockWebServer()
    private lateinit var service: NYTimesService

    @Before
    fun setUp(){
        mockWebServer.start()

        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NYTimesService::class.java)
    }

    @Test
    fun testModel(){
        var popularListLiveData = PopularListLiveData(service.getMostPopular("",12,""))
        assertNotNull(popularListLiveData)
    }


    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

}