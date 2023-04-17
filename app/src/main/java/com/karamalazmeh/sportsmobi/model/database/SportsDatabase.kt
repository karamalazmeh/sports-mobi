package com.karamalazmeh.sportsmobi.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karamalazmeh.sportsmobi.model.database.dao.LeagueDao
import com.karamalazmeh.sportsmobi.model.database.dao.SportEventDao
import com.karamalazmeh.sportsmobi.model.database.dao.TeamDao
import com.karamalazmeh.sportsmobi.model.database.entity.LeagueDatabaseEntity
import com.karamalazmeh.sportsmobi.model.database.entity.SportEventDatabaseEntity
import com.karamalazmeh.sportsmobi.model.database.entity.TeamDatabaseEntity

@Database(
    entities = [
        LeagueDatabaseEntity::class,
        TeamDatabaseEntity::class,
        SportEventDatabaseEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SportsDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao
    abstract fun teamDao(): TeamDao
    abstract fun sportEventDao(): SportEventDao

    companion object {
        private const val DATABASE_NAME = "my_database.db"

        @Volatile
        private var INSTANCE: SportsDatabase? = null

        fun getInstance(context: Context): SportsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SportsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}






/*
this is from a working reference

@Database(entities = [SportEventDatabaseEntity::class, TeamDatabaseEntity::class, LeagueDatabaseEntity::class], version = 1, exportSchema = false)
abstract class SportsDatabase: RoomDatabase() {
    abstract val sportsDao : DatabaseInterface
}

private lateinit var INSTANCE: SportsDatabase

fun getDatabase(context: Context) : SportsDatabase {
    synchronized(SportsDatabase:: class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SportsDatabase::class.java,
                "leagues")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}*/
