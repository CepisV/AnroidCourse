package kz.step.jsonplaceholder.domain.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Photo(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
) : Parcelable