package com.karamalazmeh.sportsmobi.model.network.thesportsdbapi

import timber.log.Timber

object TheSportsDbApi {
    val retrofitService : SportsApiService by lazy {
        retrofit.create(SportsApiService::class.java)
    }
}