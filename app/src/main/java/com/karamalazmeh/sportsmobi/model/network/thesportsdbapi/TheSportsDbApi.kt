package com.karamalazmeh.sportsmobi.model.network.thesportsdbapi

object TheSportsDbApi {
    val retrofitService : SportsApiService by lazy {
        retrofit.create(SportsApiService::class.java)
    }
}