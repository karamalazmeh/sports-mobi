package com.karamalazmeh.sportsmobi.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class League(
    val id: Int,
    val name: String,
    val sport: String,
    val leagueAlternate: String,
) : Parcelable
