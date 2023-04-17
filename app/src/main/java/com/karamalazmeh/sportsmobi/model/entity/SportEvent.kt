package com.karamalazmeh.sportsmobi.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SportEvent(
    val id: Int,
    val name: String,
    val round: Int,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int,
    val awayScore: Int,
    val dateEvent: String,
    val venue: String,
    val country: String,
) : Parcelable
