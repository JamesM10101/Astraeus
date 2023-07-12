package com.jamesm10101.astraeus.apis

import com.jamesm10101.astraeus.data.NasaIVL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val PLANETARY_URL = "https://images-api.nasa.gov"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(PLANETARY_URL).build()

interface NasaIVLAPIService {
    @GET("/search")
    suspend fun imageSearch(
        @Query("page_size") pageSize: Int = 25,
        @Query("page") page: Int = 1,
        @Query("media_type") mediaType: String = "image",
        @Query("q") query: String? = null,
        @Query("title") title: String? = null,
        @Query("center") center: String? = null,
        @Query("description") description: String? = null,
        @Query("description_508") description508: String? = null,
        @Query("keywords") keywords: String? = null,
        @Query("location") location: String? = null,
        @Query("nasa_id") nasaId: String? = null,
        @Query("photographer") photographer: String? = null,
        @Query("secondary_creator") secondaryCreator: String? = null,
        @Query("year_start") yearStart: String? = null,
        @Query("year_end") yearEnd: String? = null,
    ): NasaIVL
}

object NasaIVLAPI {
    val retrofitService: NasaIVLAPIService by lazy { retrofit.create(NasaIVLAPIService::class.java) }
}