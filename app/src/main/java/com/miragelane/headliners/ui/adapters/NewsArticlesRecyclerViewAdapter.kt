package com.miragelane.headliners.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miragelane.headliners.api.models.NewsArticle
import com.miragelane.headliners.ui.views.NewsArticleView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

/**
 * Keeps track of the data to show in a headlines list.
 */
class NewsArticlesRecyclerViewAdapter(val articles: List<NewsArticle>): RecyclerView.Adapter<NewsArticleViewHolder>() {

    //region State

    private val itemClickedSubject: PublishSubject<Int> = PublishSubject.create()

    //endregion

    //region Properties

    /**
     * An observable of clicked items.
     */
    val itemClickObservable: Observable<NewsArticle>
        get() {
            return this.itemClickedSubject
                .map {
                    this.articles[it]
                }
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
        }

    //endregion

    //region Overrides

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        return NewsArticleViewHolder(NewsArticleView(parent.context), this.itemClickedSubject)
    }

    override fun getItemCount(): Int {
        return this.articles.size
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        holder.articleView.newsArticle = this.articles[position]
    }

    //endregion

}

class NewsArticleViewHolder(val articleView: NewsArticleView, private val itemClickedSubject: PublishSubject<Int>): RecyclerView.ViewHolder(articleView) {
    init {
        // Add a click listener
        this.articleView.setOnClickListener {
            this.itemClickedSubject.onNext(this.adapterPosition)
        }
    }
}