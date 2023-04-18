package com.karamalazmeh.sportsmobi.model.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
data class LeagueResponse (@Json(name = "leagues") val leagues: List<League>) {
}

@JsonClass(generateAdapter = true)
@Parcelize
data class League(
    @Json(name = "idLeague") val id: Int,
    @Json(name = "strLeague") val name: String,
    @Json(name = "strSport") val sport: String,
    @Json(name = "strLeagueAlternate") val leagueAlternate: String?,
) : Parcelable
