package com.jamesm10101.astraeus.apis

import com.jamesm10101.astraeus.BuildConfig
import com.jamesm10101.astraeus.data.APOD
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val PLANETARY_URL = "https://api.nasa.gov/planetary/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(PLANETARY_URL)
    .build()

interface PlanetaryAPIService {
    @GET("apod")
    suspend fun getCurrentApod(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY,
        @Query("thumbs") thumbs: Boolean = true,
    ): APOD

    @GET("apod")
    suspend fun getApodByDate(
        @Query("date") date: String,
        @Query("thumbs") thumbs: Boolean = true,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): APOD

    @GET("apod")
    suspend fun getApodByDateRange(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("thumbs") thumbs: Boolean = true,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<APOD>

    @GET("apod?count=1")
    suspend fun getRandomApod(
        @Query("thumbs") thumbs: Boolean = true,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<APOD>

    @GET("apod")
    suspend fun getRandomApod(
        @Query("count") count: Int,
        @Query("thumbs") thumbs: Boolean = true,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<APOD>
}

object PlanetaryAPI {
    val retrofitService: PlanetaryAPIService by lazy { retrofit.create(PlanetaryAPIService::class.java) }
}
