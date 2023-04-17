package com.karamalazmeh.sportsmobi.model.network.thesportsdbapi

import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.entity.Team
import com.karamalazmeh.sportsmobi.model.entity.TeamResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApiService{


    @GET("all_leagues.php")
    suspend fun getLeagues() : Call<ResponseBody>

    @GET("search_all_teams.php")
    suspend fun getTeams(
        @Query("l")
        league: String = "English Premier League",
    ) : TeamResponse

    @GET("eventslast.php")
    suspend fun getEvents(
        @Query("id")
        team: Int,
    ) : List<ResponseBody>
}
