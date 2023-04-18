package com.karamalazmeh.sportsmobi.model.network.repository

import androidx.lifecycle.*
import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.database.SportsDatabase
import com.karamalazmeh.sportsmobi.model.entity.League
import com.karamalazmeh.sportsmobi.model.entity.Team
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.ResultsApiFilter
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApi
import kotlinx.coroutines.*
import timber.log.Timber


class ResultsRepository (private val sportsDatabase: SportsDatabase)  {

    private val _filter = MutableLiveData(ResultsApiFilter.SHOW_WEEK)


    val results: LiveData<List<SportEvent>> = Transformations.switchMap(_filter) { filter ->
        MutableLiveData(listOf<SportEvent>())
    }

    private var _teams = MutableLiveData(listOf<Team>())


    val teams : LiveData<List<Team>>
    get() = _teams

    private var _leagues = MutableLiveData(listOf<League>())


    val leagues : LiveData<List<League>>
        get() = _leagues

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun refreshLeaguesAndSports() {
        GlobalScope.launch(Dispatchers.IO) {
            _teams.postValue(TheSportsDbApi.retrofitService.getTeams().teams)
            _leagues.postValue(TheSportsDbApi.retrofitService.getLeagues().leagues)

        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    suspend fun refreshResults(selectedTeam: Team) {

        GlobalScope.launch(Dispatchers.IO) {
            val eventResults = TheSportsDbApi.retrofitService.getEvents(selectedTeam.id).results
            Timber.i(eventResults.toString())

        }
    }



    fun filterResults(filter: ResultsApiFilter){
        _filter.value = filter
    }

}