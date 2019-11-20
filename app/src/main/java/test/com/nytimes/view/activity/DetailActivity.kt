package test.com.nytimes.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import test.com.nytimes.MOST_POPULAR_ITEM
import test.com.nytimes.R
import test.com.nytimes.databinding.ActivityDetailBinding
import test.com.nytimes.model.MostPopularResult

/**
 *  This screen displays the details of a specific most popular news selected by the user
 */
class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val parcel = intent.getParcelableExtra<MostPopularResult>(MOST_POPULAR_ITEM)
        bind(parcel)
    }

    private fun bind(parcel: MostPopularResult) {

        binding.apply {
            articleTitle.text = parcel.title
            abstractInformation.text = parcel.abstract
            articleWrittenBy.text = parcel.byline
            url.text = "URL: " + parcel.url
            source.text = "Source: " + parcel.source

            invalidateAll()
        }
    }
}
