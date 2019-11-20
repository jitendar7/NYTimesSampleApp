package test.com.nytimes.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Networking using Retrofit2

private const val BASE_URL = "http://api.nytimes.com/svc/"

class APIClient {

    companion object {
        val client: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}