package com.karamalazmeh.sportsmobi.model.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
data class SportEventResponse (@Json(name = "results") val results: List<SportEvent>) {
}

@JsonClass(generateAdapter = true)
@Parcelize
data class SportEvent(
    @Json(name = "idEvent") val id: Int,
    @Json(name = "strEvent") val name: String,
    @Json(name = "intRound") val round: Int,
    @Json(name = "strHomeTeam") val homeTeam: String,
    @Json(name = "strAwayTeam") val awayTeam: String,
    @Json(name = "intHomeScore") val homeScore: Int,
    @Json(name = "intAwayScore") val awayScore: Int,
    @Json(name = "dateEvent") val dateEvent: String,
    @Json(name = "strVenue") val venue: String,
    @Json(name = "strCountry") val country: String,
) : Parcelable
