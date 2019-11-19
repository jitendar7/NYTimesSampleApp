package test.com.nytimes.model

import org.junit.Test
import org.junit.Assert.*

class MostPopularResponseTest {

    private lateinit var mostPopularResponse: MostPopularResponse

    @Test
    fun testModelData() {
        mostPopularResponse = MostPopularResponse(
                status = "SUCCESS",
                copyright = "copyright@jitendar",
                numOfResults = 12,
                resultList = listOf()
        )

        assertNotNull(mostPopularResponse.status)
        assertNotNull(mostPopularResponse.copyright)
        assertNotNull(mostPopularResponse.numOfResults)
        assertNotNull(mostPopularResponse.resultList)
    }


}