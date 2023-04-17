package com.karamalazmeh.sportsmobi.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "sport_events")
data class SportEventDatabaseEntity(
    @PrimaryKey
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
)
