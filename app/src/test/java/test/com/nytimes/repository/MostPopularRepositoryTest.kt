package test.com.nytimes.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.com.nytimes.data.cache.CacheManager
import test.com.nytimes.data.remote.NYTimesService
import test.com.nytimes.model.MostPopularResponse
import java.net.HttpURLConnection.HTTP_OK


class MostPopularRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mostPopularRepository: MostPopularRepository

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
    fun testRepository() {

        //Assign the response
        val response = MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody("{}")
        mockWebServer.enqueue(response)

        mostPopularRepository = MostPopularRepository(service)

        assertNotNull(service.getMostPopular("",12,"").execute())

        mostPopularRepository.getMostPopularList("",12,"")

        CacheManager.save(MostPopularResponse("","",10, listOf()))
        mostPopularRepository.getMostPopularList("",11,"")

        mostPopularRepository.buildCachedLiveData(MostPopularResponse("data_status","copyright",12, listOf()))
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}