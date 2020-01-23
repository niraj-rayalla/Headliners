package com.miragelane.headliners.ui.views

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.miragelane.headliners.R
import com.miragelane.headliners.api.models.NewsArticle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_article.view.*

/**
 * The view that shows a single news article.
 */
class NewsArticleView(context: Context): ConstraintLayout(context) {

    //region Init

    init {
        // Inflate the view layout for this view
        View.inflate(context, R.layout.view_article, this)
    }

    //endregion

    //region Properties

    var newsArticle: NewsArticle? = null
        set(value) {
            field = value
            value?.also { this.setData(it) }
        }

    //endregion

    //region Private Methods

    private fun setData(article: NewsArticle) {
        // Set the needed texts
        this.titleTextView.text = article.title
        this.descriptionTextView.text = article.description

        // Get the image for the article
        Picasso.with(this.context).load(article.urlToImage).into(this.imageView)
    }

    //endregion
}