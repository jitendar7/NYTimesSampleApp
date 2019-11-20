package test.com.nytimes.repository

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.com.nytimes.data.remote.NYTimesService
import java.net.HttpURLConnection.HTTP_OK


class MostPopularRepositoryTest {

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
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}