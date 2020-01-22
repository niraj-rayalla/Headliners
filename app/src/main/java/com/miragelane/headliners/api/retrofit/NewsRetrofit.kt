package com.miragelane.headliners.api.retrofit

import com.miragelane.headliners.Constants
import com.miragelane.headliners.api.models.TopHeadlines
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Retrofit class that handles all api calls to the news api.
 */
interface NewsRetrofit {

    /**
     * Gets the top headline articles.
     */
    @GET("/top-headlines?country=us&apiKey=${Constants.HEADLNES_API_KEY}")
    fun getTopHeadlines(): Single<TopHeadlines>

}