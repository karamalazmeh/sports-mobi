package com.karamalazmeh.sportsmobi.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karamalazmeh.sportsmobi.model.database.entity.TeamDatabaseEntity

@Dao
interface TeamDao {

    @Query("SELECT * FROM teams ORDER BY id ASC")
    fun getTeams(): LiveData<List<TeamDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg teams: TeamDatabaseEntity)
}