package com.miragelane.headliners.api.models

import org.threeten.bp.Instant

/**
 * The model for a single news article.
 */
data class NewsArticle(val author: String,
                       val title: String,
                       val description: String,
                       val url: String,
                       val urlToImage: String,
                       val publishedAt: Instant,
                       val content: String)