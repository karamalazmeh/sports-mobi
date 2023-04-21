package com.karamalazmeh.sportsmobi.model.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karamalazmeh.sportsmobi.model.entity.League
import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.entity.Team
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class ResultsRepository ()  {


    private var _teams = MutableLiveData(listOf<Team>())

    val teams : LiveData<List<Team>>
    get() = _teams

    private var _leagues = MutableLiveData(listOf<League>())


    val leagues : LiveData<List<League>>
    get() = _leagues

    private var _results = MutableLiveData(listOf<SportEvent>())

    val results : LiveData<List<SportEvent>>
        get() = _results

    suspend fun refreshLeagues() {
        withContext(Dispatchers.IO) {
            _leagues.postValue(
                TheSportsDbApi.retrofitService.getLeagues().leagues.filter { it.sport == "Soccer"}
            )

        }
    }

    suspend fun refreshTeams(league: String  = "English Premier League") {
        withContext(Dispatchers.IO) {
            _teams.postValue(TheSportsDbApi.retrofitService.getTeams(league).teams)
        }
    }

    suspend fun refreshResults(selectedTeam: Team) {
        withContext(Dispatchers.IO) {
            _results.postValue(TheSportsDbApi.retrofitService.getEvents(selectedTeam.id).results)
            Timber.i(results.toString())
        }
    }

}