package test.com.nytimes.model

import android.os.Parcel
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MostPopularResultTest {

    private lateinit var mostPopularResult: MostPopularResult

    @Test
    fun testModelData(){

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

        var parcel: Parcel = Parcel.obtain()
        mostPopularResult.writeToParcel(parcel,mostPopularResult.describeContents())
        parcel.setDataPosition(0)

        val mostPopularResultParcel = MostPopularResult.CREATOR.createFromParcel(parcel)
        assertEquals(mostPopularResultParcel.url,mostPopularResult.url)

    }

}