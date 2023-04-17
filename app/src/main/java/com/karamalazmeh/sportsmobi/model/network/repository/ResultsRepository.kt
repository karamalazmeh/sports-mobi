package com.karamalazmeh.sportsmobi.model.network.repository

import androidx.lifecycle.*
import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.database.SportsDatabase
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.ResultsApiFilter
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApi
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.await
import timber.log.Timber


class ResultsRepository (private val sportsDatabase: SportsDatabase)  {

    private val _filter = MutableLiveData(ResultsApiFilter.SHOW_WEEK)


    val results: LiveData<List<SportEvent>> = Transformations.switchMap(_filter) { filter ->
        MutableLiveData(listOf<SportEvent>())
    }


    @OptIn(DelicateCoroutinesApi::class)
    suspend fun refreshResults() {

        var teamId = 133664

        var sportEvents: List<SportEvent>
        GlobalScope.launch(Dispatchers.IO) {
            val results = TheSportsDbApi.retrofitService.getTeams()
            Timber.i(results.toString())

        }
    }
    fun filterResults(filter: ResultsApiFilter){
        _filter.value = filter
    }

}