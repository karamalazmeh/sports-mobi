package com.karamalazmeh.sportsmobi.view.main

import android.app.Application
import androidx.lifecycle.*
import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.database.SportsDatabase
import com.karamalazmeh.sportsmobi.model.network.repository.ResultsRepository
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.ResultsApiFilter
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




    // Get database and refresh repository

    private val database = SportsDatabase.getInstance(application)
    init {
        Timber.i("database created")
    }
    private val resultsRepository = ResultsRepository(database)

    val resultsList : LiveData<List<SportEvent>>
    get() = resultsRepository.results

    init{
        viewModelScope.launch {
//            resultsRepository.refreshResults()
        }
    }

    fun refreshResults() {
        viewModelScope.launch {
            resultsRepository.refreshResults()
        }
    }


    private val _navigateToSelectedSportEvent = MutableLiveData<SportEvent>()
    val navigateToSelectedProperty: LiveData<SportEvent>
        get() = _navigateToSelectedSportEvent

    fun displayPropertyDetails(sportEvent: SportEvent) {
        _navigateToSelectedSportEvent.value = sportEvent
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedSportEvent.value = null
    }


    fun updateFilter(filter: ResultsApiFilter) {
        resultsRepository.filterResults(filter)
    }


}
