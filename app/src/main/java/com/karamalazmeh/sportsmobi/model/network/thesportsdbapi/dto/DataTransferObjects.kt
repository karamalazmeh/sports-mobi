import com.karamalazmeh.sportsmobi.model.database.entity.SportEventDatabaseEntity
import com.karamalazmeh.sportsmobi.model.entity.SportEvent

// Future update for database local caching
fun List<SportEvent>.asDatabaseModel(): Array<SportEventDatabaseEntity> {
    return this.map {
        SportEventDatabaseEntity(
            id = it.id,
            name = it.name,
            round = it.round,
            homeTeam = it.homeTeam,
            awayTeam = it.awayTeam,
            homeScore = it.homeScore,
            awayScore = it.awayScore,
            dateEvent = it.dateEvent,
            venue = it.venue,
            country = it.country,
        )
    }.toTypedArray()
}
