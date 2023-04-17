package com.karamalazmeh.sportsmobi.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "teams")
data class TeamDatabaseEntity (
    @PrimaryKey
    val id: Int,
    val sport: String,
    val name: String,
    val venue: String,
    )