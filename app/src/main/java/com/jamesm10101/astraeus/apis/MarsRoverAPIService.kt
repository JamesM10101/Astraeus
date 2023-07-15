package com.jamesm10101.astraeus.apis

import com.jamesm10101.astraeus.BuildConfig
import com.jamesm10101.astraeus.data.MarsRoverDetails
import com.jamesm10101.astraeus.data.MarsRoverLatestPhotos
import com.jamesm10101.astraeus.data.MarsRoverPhotos
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

private const val MARS_ROVER_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(MARS_ROVER_URL)
    .build()

interface MarsRoverAPIService {

    @GET("{rover}")
    suspend fun getRoverDetails(
        @Path("rover") rover: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): MarsRoverDetails

    @GET("{rover}/latest_photos")
    suspend fun getRoverLatestPhotos(
        @Path("rover") rover: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): MarsRoverLatestPhotos

    @GET("{rover}/latest_photos")
    suspend fun getRoverLatestPhotos(
        @Path("rover") rover: String,
        @Query("camera") camera: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): MarsRoverLatestPhotos

    @GET("{rover}/photos")
    suspend fun getRoverPhotosSol(
        @Path("rover") rover: String,
        @Query("sol") sol: Int,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): MarsRoverPhotos

    @GET("{rover}/photos")
    suspend fun getRoverPhotosSol(
        @Path("rover") rover: String,
        @Query("sol") sol: Int,
        @Query("page") page: Int = 1,
        @Query("camera") camera: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): MarsRoverPhotos

    @GET("{rover}/photos")
    suspend fun getRoverPhotosEarthDate(
        @Path("rover") rover: String,
        @Query("earth_date") earthDate: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): MarsRoverPhotos

    @GET("{rover}/photos")
    suspend fun getRoverPhotosEarthDate(
        @Path("rover") rover: String,
        @Query("earth_date") earthDate: String,
        @Query("page") page: Int = 1,
        @Query("camera") camera: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): MarsRoverPhotos

}

object MarsRoverAPI {
    val retrofitService: MarsRoverAPIService by lazy { retrofit.create(MarsRoverAPIService::class.java) }
}