package com.karamalazmeh.sportsmobi.model.network.thesportsdbapi

import com.karamalazmeh.sportsmobi.model.entity.LeagueResponse
import com.karamalazmeh.sportsmobi.model.entity.SportEventResponse
import com.karamalazmeh.sportsmobi.model.entity.TeamResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApiService{


    @GET("all_leagues.php")
    suspend fun getLeagues() : LeagueResponse

    @GET("search_all_teams.php")
    suspend fun getTeams(
        @Query("l")
        league: String,
    ) : TeamResponse

    @GET("eventslast.php")
    suspend fun getEvents(
        @Query("id")
        team: Int,
    ) : SportEventResponse
}
