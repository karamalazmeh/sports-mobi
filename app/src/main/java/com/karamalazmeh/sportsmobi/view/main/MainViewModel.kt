package com.karamalazmeh.sportsmobi.view.main

import android.app.Application
import androidx.lifecycle.*
import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.database.SportsDatabase
import com.karamalazmeh.sportsmobi.model.entity.Team
import com.karamalazmeh.sportsmobi.model.network.repository.ResultsRepository
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApiStatus
import kotlinx.coroutines.launch
import timber.log.Timber


class MainViewModel(application: Application) : AndroidViewModel(application) {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<TheSportsDbApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<TheSportsDbApiStatus>
        get() = _status

    // The internal MutableLiveData String that stores  of the most recent request
    private val _properties = MutableLiveData<List<SportEvent>>()

    // The external immutable LiveData for the request properties string
    val properties: LiveData<List<SportEvent>>
        get() = _properties

    private val _dominantColor = MutableLiveData(0)

    val dominantColor : LiveData<Int>
    get() = _dominantColor

    // Get database and refresh repository

    private val database = SportsDatabase.getInstance(application)
    init {
        Timber.i("database created")
    }
    private val resultsRepository = ResultsRepository(database)

    val resultsList : LiveData<List<SportEvent>>
        get() = resultsRepository.results


    val teamsEntries : LiveData<List<String>> = Transformations.map(resultsRepository.teams) { teams ->
        teams.map { team -> team.name }
    }


    val leaguesEntries : LiveData<List<String>> = Transformations.map(resultsRepository.leagues) { leagues ->
        leagues.map { league -> league.name }
    }

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _selectedTeam = MutableLiveData<Team>()

    // The external immutable LiveData for the request status String
    val selectedTeam: LiveData<Team>
        get() = _selectedTeam

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _selectedLeague = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val selectedLeague: LiveData<String>
        get() = _selectedLeague

    init{
        updateLeaguesAndTeams()
    }



    fun refreshResults() {
        viewModelScope.launch {
            try {
                _status.value = TheSportsDbApiStatus.LOADING
                _selectedTeam.value?.let {
                    resultsRepository.refreshResults(it)
                    }
                _status.value = TheSportsDbApiStatus.DONE
                } catch(e: Exception){
                    _status.value = TheSportsDbApiStatus.ERROR
            }
        }
    }

    fun updateDominantColor(color: Int) {
        _dominantColor.value = color
    }

    private val _navigateToSelectedSportEvent = MutableLiveData<SportEvent>()
    val navigateToSelectedEvent: LiveData<SportEvent>
        get() = _navigateToSelectedSportEvent

    fun displayEventResultsDetails(sportEvent: SportEvent) {
        _navigateToSelectedSportEvent.value = sportEvent
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedSportEvent.value = null
    }



    fun updateSelectedTeam(position: Int){
        _selectedTeam.value = resultsRepository.teams.value?.get(position)
    }

    fun updateTeams(league: String) {
        viewModelScope.launch {
            try {
                _status.value = TheSportsDbApiStatus.LOADING
                resultsRepository.refreshTeams(league)
                updateSelectedTeam(0)
                refreshResults()
                _status.value = TheSportsDbApiStatus.DONE
            } catch (e: Exception) {
                _status.value = TheSportsDbApiStatus.ERROR
            }
        }
    }

    fun updateLeaguesAndTeams() {
        viewModelScope.launch {
            try {
                _status.value = TheSportsDbApiStatus.LOADING
                resultsRepository.refreshLeagues()
                resultsRepository.refreshTeams()
                _status.value = TheSportsDbApiStatus.DONE
            } catch(e: Exception) {
                _status.value = TheSportsDbApiStatus.ERROR
            }
        }
    }


}
