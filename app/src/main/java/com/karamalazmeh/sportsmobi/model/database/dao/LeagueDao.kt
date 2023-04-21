package com.karamalazmeh.sportsmobi.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karamalazmeh.sportsmobi.model.database.entity.LeagueDatabaseEntity

// Future update to include db for local caching

@Dao
interface LeagueDao {

    @Query("SELECT * FROM leagues ORDER BY id ASC")
    fun getLeagues(): LiveData<List<LeagueDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg leagues: LeagueDatabaseEntity)
}