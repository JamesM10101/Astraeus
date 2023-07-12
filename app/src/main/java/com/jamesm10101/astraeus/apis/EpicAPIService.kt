package com.jamesm10101.astraeus.apis

import com.jamesm10101.astraeus.BuildConfig
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.data.EpicDate
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val PLANETARY_URL = "https://api.nasa.gov/EPIC/api/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(PLANETARY_URL).build()

interface EpicAPIService {

    @GET("{type}/all")
    suspend fun getAll(
        @Path("type") type: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<EpicDate>

    @GET("{type}/date/{date}")
    suspend fun getByDate(
        @Path("type") type: String,
        @Path("date") date: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<Epic>

    @GET("natural")
    suspend fun getNatural(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<Epic>

    @GET("enhanced")
    suspend fun getEnhanced(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<Epic>
    
    @GET("aerosol")
    suspend fun getAerosol(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<Epic>

    @GET("cloud")
    suspend fun getCloud(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): List<Epic>
}

object EpicAPI {
    val retrofitService: EpicAPIService by lazy { retrofit.create(EpicAPIService::class.java) }
}
