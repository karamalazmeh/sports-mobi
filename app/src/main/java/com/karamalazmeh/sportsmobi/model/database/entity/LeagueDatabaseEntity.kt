package com.karamalazmeh.sportsmobi.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "leagues")
data class LeagueDatabaseEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val sport: String,
    val leagueAlternate: String,
)