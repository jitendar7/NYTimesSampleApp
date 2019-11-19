package test.com.nytimes.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class MostPopularParametersTest {

    private lateinit var mostPopularParameters: MostPopularParameters

    @Test
    fun testModelData() {
        mostPopularParameters = MostPopularParameters(
                "section", 7, "sample_api_key"
        )

        assertEquals("section", mostPopularParameters.section)
        assertEquals(7, mostPopularParameters.period)
        assertEquals("sample_api_key", mostPopularParameters.apiKey)
        assertNotNull(mostPopularParameters.toString())
        assertNotNull(mostPopularParameters.hashCode())
    }

}