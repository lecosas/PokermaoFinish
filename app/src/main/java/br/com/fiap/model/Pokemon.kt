package br.com.fiap.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon (
    var number: String,
    var name: String,
    var ps: Int,
    var attack: Int,
    var defense: Int,
    var velocity: Int,
    var description: String,
    var imageURL: String
) : Parcelable