package com.miragelane.headliners.ui.viewmodels

import com.miragelane.headliners.api.HeadlinersApi
import com.miragelane.headliners.extensions.disposedBy
import com.miragelane.headliners.ui.adapters.NewsArticlesRecyclerViewAdapter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * ViewModel for HeadlinesActivity.
 */
class HeadlinesActivityViewModel: HeadlinersViewModel() {

    //region State

    val adapter = NewsArticlesRecyclerViewAdapter(emptyList())

    //endregion

    //region Init

    init {
        // Pull the data at start
        this.refreshData().subscribe({}, {}).disposedBy(this.lifecycleDisposable)
    }

    //endregion

    //region Commands

    /**
     * Pulls the latest data from the server.
     */
    fun refreshData(): Completable {
        return Completable.create { completableEmitter ->
            // Pull the data from the api
            HeadlinersApi.getTopHeadlines().observeOn(AndroidSchedulers.mainThread()).subscribe({
                this.adapter.articles = it.articles
                completableEmitter.onComplete()
            }, {
                completableEmitter.onError(it)
            }).disposedBy(this.lifecycleDisposable)
        }
    }

    //endregion
}