package com.arwin.dinoshistory.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino(
    val name: String?,
    val description: String?,
    val photo: String?
) : Parcelable
