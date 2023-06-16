package com.example.cardsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Destinations(val route: String): Parcelable {
    @Parcelize
    object ScreenA: Destinations("A")

    @Parcelize
    object ScreenB: Destinations("B")

    @Parcelize
    object ScreenC: Destinations("C")
}