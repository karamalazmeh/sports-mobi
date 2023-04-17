package com.karamalazmeh.sportsmobi.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karamalazmeh.sportsmobi.model.database.entity.SportEventDatabaseEntity

@Dao
interface SportEventDao {

    @Query("SELECT * FROM sport_events ORDER BY id ASC")
    fun getSportEvents(): LiveData<List<SportEventDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg sportEvents: SportEventDatabaseEntity)
}