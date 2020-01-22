package com.miragelane.headliners.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.miragelane.headliners.Constants
import com.miragelane.headliners.api.adapters.InstantAdapter
import com.miragelane.headliners.api.models.TopHeadlines
import com.miragelane.headliners.api.retrofit.NewsRetrofit
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.Instant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Handles the http api and the
 */
object HeadlinersApi {

    //region State

    val gson: Gson = run {
        GsonBuilder()
            .registerTypeAdapter(Instant::class.java, InstantAdapter())
            .create()
    }

    private val retrofit: Retrofit = run {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(this.gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(Constants.HEADLINES_API_BASE_PATH)
            .build()
    }
    private val newsHttpApi: NewsRetrofit = this.retrofit.create(NewsRetrofit::class.java)

    //endregion

    //region Public Methods

    /**
     * Returns the top headlines from the news api.
     */
    fun getTopHeadlines(): Single<TopHeadlines> {
        return this.newsHttpApi.getVehicleData()
    }

    //endregion

}