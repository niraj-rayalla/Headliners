package com.miragelane.headliners.api.models

/**
 * Model from the news api that contains all the articles for the top headlines.
 */
data class TopHeadlines(val status: String,
                        val totalResults: Int,
                        val articles: List<NewsArticle>)