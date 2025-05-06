package kz.step.jsonplaceholder.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToDo(
    val userId : Int,
    val id : Int,
    val title : String,
    val completed : Boolean
) : Parcelable