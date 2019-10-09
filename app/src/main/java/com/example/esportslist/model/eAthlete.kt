package com.example.esportslist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class eAthlete(
    var id: Int,
    var name: String,
    var handle: String,
    var game: String,
    var team: String,
    var nationality: String ): Parcelable