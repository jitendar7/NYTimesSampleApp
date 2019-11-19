package test.com.nytimes.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import test.com.nytimes.model.MostPopularResponse

interface NYTimesService {

    @GET("mostpopular/v2/mostviewed/{section}/{period}.json?")
    fun getMostPopular(@Path("section") section: String,
                       @Path("period") period: Int,
                       @Query("api-key") apiKey: String): Call<MostPopularResponse>
}