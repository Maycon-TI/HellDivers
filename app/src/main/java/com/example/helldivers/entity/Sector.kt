package com.example.helldivers.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Sector(
    var symbol: String,
    var nameSector: String,
    var liberated: Float
): Parcelable