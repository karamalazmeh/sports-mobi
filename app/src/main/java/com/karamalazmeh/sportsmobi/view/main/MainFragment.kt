package com.karamalazmeh.sportsmobi.view.main

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.karamalazmeh.sportsmobi.R
import com.karamalazmeh.sportsmobi.databinding.FragmentMainBinding
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApiStatus
import com.karamalazmeh.sportsmobi.view.util.ResultsListAdapter
import timber.log.Timber

class MainFragment : Fragment() {


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var menu: Menu? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.resultsRecycler.adapter = ResultsListAdapter(ResultsListAdapter.OnClickListener {
            viewModel.displayEventResultsDetails(it)
        })


        setHasOptionsMenu(true)


        // Creating adapter for spinner
        val dataAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mutableListOf()). also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        // Drop down layout style - list view with radio button
        binding.teamSpinner.adapter = dataAdapter



        binding.teamSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.updateSelectedTeam(position)
                viewModel.refreshResults()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it == TheSportsDbApiStatus.ERROR ) {
                val snackbar = Snackbar.make(requireView(), R.string.connection_error, Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("Retry") {
                    viewModel.updateLeaguesAndTeams()
                }
                snackbar.show()
            }
        }

        viewModel.navigateToSelectedEvent.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        viewModel.teamsEntries.observe(viewLifecycleOwner) {teams ->
            dataAdapter.clear()
            dataAdapter.addAll(teams)
        }


        viewModel.leaguesEntries.observe(viewLifecycleOwner) { leagues ->
            menu?.let { menu ->
                for (league in leagues) {
                    menu.add(league)
                }
                Timber.i("Menu is created from observer with ${menu.size} menu items")
            }
        }

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        if (this.menu == null) {
            this.menu = menu
            return
        }
        viewModel.leaguesEntries.value?.let {leagues ->
            for (league in leagues) {
                menu.add(league)
            }
            Timber.i("Menu is created with ${menu.size} items")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateTeams(item.title as String)

        return true
    }

}
