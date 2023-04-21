package com.karamalazmeh.sportsmobi.view.main

import android.app.Application
import androidx.lifecycle.*
import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.entity.Team
import com.karamalazmeh.sportsmobi.model.network.repository.ResultsRepository
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApiStatus
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<TheSportsDbApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<TheSportsDbApiStatus>
        get() = _status

    // future feature to get dominant color from banner
    private val _dominantColor = MutableLiveData(0)

    val dominantColor : LiveData<Int>
    get() = _dominantColor

    // Refresh repository
    private val resultsRepository = ResultsRepository()

    // immutable LiveData to get leagues entries
    val leaguesEntries : LiveData<List<String>> = Transformations.map(resultsRepository.leagues) { leagues ->
        leagues.map { league -> league.name }
    }

    // immutable LiveData to get teams entries
    val teamsEntries : LiveData<List<String>> = Transformations.map(resultsRepository.teams) { teams ->
        teams.map { team -> team.name }
    }

    // sports event results to user
    val resultsList : LiveData<List<SportEvent>>
        get() = resultsRepository.results


    // The internal MutableLiveData String that stores the status of the most recent request
    private val _selectedTeam = MutableLiveData<Team>()

    // The external immutable LiveData for the request status String
    val selectedTeam: LiveData<Team>
        get() = _selectedTeam


    // Update leagues and teams upon application start
    init{
        updateLeaguesAndTeams()
    }

    // Data handling:

    // refresh results for the selected team
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

    // Update Teams from repository
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

    // Update leagues from repository
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

    // Update selected team
    fun updateSelectedTeam(position: Int){
        _selectedTeam.value = resultsRepository.teams.value?.get(position)
    }

    // Navigation trigger
    private val _navigateToSelectedSportEvent = MutableLiveData<SportEvent>()
    val navigateToSelectedEvent: LiveData<SportEvent>
        get() = _navigateToSelectedSportEvent

    fun displayEventResultsDetails(sportEvent: SportEvent) {
        _navigateToSelectedSportEvent.value = sportEvent
    }
    fun displayEventResultsDetailsComplete() {
        _navigateToSelectedSportEvent.value = null
    }


    // future feature for dominant color
    fun updateDominantColor(color: Int) {
        _dominantColor.value = color
    }


}
