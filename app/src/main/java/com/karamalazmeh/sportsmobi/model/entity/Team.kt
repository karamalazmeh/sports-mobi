package com.karamalazmeh.sportsmobi.model.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize



@JsonClass(generateAdapter = true)
data class TeamResponse (@Json(name = "teams") val teams: List<Team>) {
}

@JsonClass(generateAdapter = true)
data class Team (
    @Json(name = "idTeam") val id: Int,
    @Json(name = "strSport") val sport: String,
    @Json(name = "strTeam") val name: String,
    @Json(name = "strStadium") val venue: String,

    )