package com.jamesm10101.astraeus.state

import android.os.Parcelable
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.data.ApodSortType
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.data.EpicSortType
import com.jamesm10101.astraeus.data.IVLResultSortType
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.data.MarsRoverPhotoSortType
import com.jamesm10101.astraeus.data.NasaIVLImageData
import kotlinx.parcelize.Parcelize

@Parcelize
data class SavedApodState(
    val apods: List<APOD> = emptyList(),
    val apodSortType: ApodSortType = ApodSortType.ID,
) : Parcelable

@Parcelize
data class SavedEpicState(
    val epics: List<Epic> = emptyList(),
    val epicSortType: EpicSortType = EpicSortType.ID,
) : Parcelable

@Parcelize
data class SavedRoverPhotosState(
    val roverPhotos: List<MarsRoverPhoto> = emptyList(),
    val marsRoverPhotoSortType: MarsRoverPhotoSortType = MarsRoverPhotoSortType.ID
) : Parcelable

@Parcelize
data class SavedIVLImageState(
    val ivl: List<NasaIVLImageData> = emptyList(),
    val ivlResultSortType: IVLResultSortType = IVLResultSortType.ID,
) : Parcelable