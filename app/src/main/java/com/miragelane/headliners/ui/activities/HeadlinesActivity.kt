package com.miragelane.headliners.ui.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.miragelane.headliners.api.models.NewsArticle
import com.miragelane.headliners.extensions.disposedBy
import com.miragelane.headliners.ui.viewmodels.HeadlinesActivityViewModel

import kotlinx.android.synthetic.main.activity_headlines.*
import android.content.Intent
import android.net.Uri
import com.miragelane.headliners.R
import java.lang.Exception


/**
 * Shows all headlines. Each headline will be clickable and it opens the headline in the browser.
 */
class HeadlinesActivity : HeadlinersActivity() {

    //region State

    private lateinit var viewModel: HeadlinesActivityViewModel

    //endregion

    //region Setup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headlines)

        // Create the view model
        this.viewModel = ViewModelProviders.of(this).get(HeadlinesActivityViewModel::class.java)

        // Recycler view
        this.setupRecyclerView()

        // Refreshing
        this.setupRefresher()
    }

    private fun setupRecyclerView() {
        this.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        this.recyclerView.adapter = this.viewModel.adapter

        // Listen to item clicks
        this.viewModel.adapter.itemClickObservable.subscribe {
            this.showArticleInBrowser(it)
        }.disposedBy(this.lifecycleDisposable)
    }

    /**
     * Listens to when the user wants to refresh the data using the swipe refresh view.
     */
    private fun setupRefresher() {
        this.swipeRefreshLayout.setOnRefreshListener {
            this.viewModel.refreshData().doFinally {
                this.swipeRefreshLayout.isRefreshing = false
            }.subscribe({}, {}).disposedBy(this.lifecycleDisposable)
        }
    }

    //endregion

    //region Overrides

    override fun onDestroy() {
        super.onDestroy()
        this.swipeRefreshLayout.setOnRefreshListener(null)
    }

    //endregion

    //region Private Methods

    private fun showArticleInBrowser(article: NewsArticle) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            startActivity(browserIntent)
        }
        catch (e: Exception) {
            Snackbar.make(this.rootView, R.string.fail_open_article_in_browser, Snackbar.LENGTH_SHORT)
        }
    }

    //endregion
}
