package test.com.nytimes.model

import android.os.Parcel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import test.com.nytimes.MockParcel

class MostPopularResultTest {

    private lateinit var mostPopularResult: MostPopularResult

    @Test
    fun testModelData() {

        mostPopularResult = MostPopularResult(
                url = "org.newyorktimes",
                adx_keywords = "keyword",
                column = "7",
                section = "data_section",
                byline = "data_byline",
                type = "data_type",
                title = "data_title",
                abstract = "data_abstract",
                published_date = "data_published_date",
                source = "data_source"
        )

        var parcel: Parcel = MockParcel.obtain()
        mostPopularResult.writeToParcel(parcel, mostPopularResult.describeContents())
        parcel.setDataPosition(0)

        val mostPopularResultParcel = MostPopularResult.createFromParcel(parcel)
        assertEquals(mostPopularResultParcel.url, mostPopularResult.url)
        assertNotNull(mostPopularResultParcel.adx_keywords)
        assertNotNull(mostPopularResultParcel.column)
        assertNotNull(mostPopularResultParcel.section)
        assertNotNull(mostPopularResultParcel.byline)
        assertNotNull(mostPopularResultParcel.type)
        assertNotNull(mostPopularResultParcel.title)
        assertNotNull(mostPopularResultParcel.abstract)
        assertNotNull(mostPopularResultParcel.published_date)
        assertNotNull(mostPopularResultParcel.source)
    }

}