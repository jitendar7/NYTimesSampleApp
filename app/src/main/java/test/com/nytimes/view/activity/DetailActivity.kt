package test.com.nytimes.view.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import test.com.nytimes.R
import test.com.nytimes.model.MostPopularResult

/**
 *  This screen displays the details of a specific most popular news selected by the user
 */
class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val parcel = intent.getParcelableExtra<MostPopularResult>(MOST_POPULAR_ITEM)

        articleTitle.text = parcel.title
        abstractInformation.text = parcel.abstract
        articleWrittenBy.text = parcel.byline
        url.text = "URL: " + parcel.url
        source.text = "Source: " + parcel.source
    }
}
