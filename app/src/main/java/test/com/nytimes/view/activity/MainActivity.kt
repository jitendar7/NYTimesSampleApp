package test.com.nytimes.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.kodein.di.generic.instance
import test.com.nytimes.API_KEY
import test.com.nytimes.MOST_POPULAR_ITEM
import test.com.nytimes.R
import test.com.nytimes.databinding.ActivityMainBinding
import test.com.nytimes.model.MostPopularParameters
import test.com.nytimes.model.MostPopularResult
import test.com.nytimes.data.remote.Resource
import test.com.nytimes.view.adapter.MostPopularRecyclerViewAdapter
import test.com.nytimes.viewmodel.MostPopularViewModel
import test.com.nytimes.viewmodel.ViewModelFactory

// First Screen which displays the results of NewYorkTimes most popular articles
class MainActivity : BaseActivity(), MostPopularRecyclerViewAdapter.OnItemClickListener {

    private val viewModelFactory: ViewModelFactory by instance()
    private lateinit var viewModel: MostPopularViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MostPopularViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.appBarMain.included.viewModel = viewModel
        binding.setLifecycleOwner(this)

        initializeRequestParameters()
        setUpWithObservables()
    }

    private fun initializeRequestParameters() {
        viewModel.initialize(MostPopularParameters(
                "all-sections",
                7,
                API_KEY))
    }

    private fun setUpWithObservables() {

        setSupportActionBar(toolbar)

        val adapter = MostPopularRecyclerViewAdapter()
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter

        viewModel.mostPopular.observe(this, Observer {
            when (it?.status) {
                Resource.Status.LOADING -> viewModel.setProgressValue(false)
                Resource.Status.ERROR -> {
                    viewModel.setProgressValue(true)
                    Snackbar.make(drawer_layout, it?.throwable?.message.toString(), Snackbar.LENGTH_LONG).show()
                }
                Resource.Status.SUCCESS -> {
                    viewModel.setProgressValue(true)
                    it.data?.let {
                        adapter.setPopularList(it.results)
                    }
                }
            }
        })

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun setOnItemClickListener(mostPopularResult: MostPopularResult) {
        val intent = Intent(this, DetailActivity::class.java)

        val args = Bundle()
        args.putParcelable(MOST_POPULAR_ITEM, mostPopularResult)
        intent.putExtras(args)

        startActivity(intent)
    }
}
