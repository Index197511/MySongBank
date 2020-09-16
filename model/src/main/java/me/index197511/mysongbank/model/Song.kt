package me.index197511.mysongbank.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
    val id: Int,
    val name: String,
    val singer: String,
    val key: Int,
    val memo: String
) : Parcelable