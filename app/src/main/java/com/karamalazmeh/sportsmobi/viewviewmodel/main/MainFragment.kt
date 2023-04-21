package com.karamalazmeh.sportsmobi.viewviewmodel.main

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.karamalazmeh.sportsmobi.R
import com.karamalazmeh.sportsmobi.databinding.FragmentMainBinding
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApiStatus
import com.karamalazmeh.sportsmobi.viewviewmodel.util.ResultsListAdapter
import com.karamalazmeh.sportsmobi.viewviewmodel.util.SpinnerListAdapter

class MainFragment : Fragment() {


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var menu: Menu? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)

        // Set binding parameters
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        binding.resultsRecycler.adapter = ResultsListAdapter(ResultsListAdapter.OnClickListener {
            viewModel.displayEventResultsDetails(it)
        })

        // Menu Items Options
        setHasOptionsMenu(true)


        // bind spinner
        val spinner = binding.teamSpinner

        // Initialize spinner list adapter
        val spinnerListAdapter = SpinnerListAdapter(requireContext())

        // bind adapter
        spinner.adapter = spinnerListAdapter

        // set spinner listener
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.updateSelectedTeam(position)
                viewModel.refreshResults()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }

        //Observers:

        // Connection error observer for api network connection
        viewModel.status.observe(viewLifecycleOwner) {
            if (it == TheSportsDbApiStatus.ERROR ) {
                val snackbar = Snackbar.make(requireView(), R.string.connection_error, Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("Retry") {
                    viewModel.updateLeaguesAndTeams()
                }
                snackbar.show()
            }
        }

        // Navigation to detailed event
        viewModel.navigateToSelectedEvent.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.displayEventResultsDetailsComplete()
            }
        }

        // Team entries observer for spinner values update
        viewModel.teamsEntries.observe(viewLifecycleOwner) {teams ->
            spinnerListAdapter.clear()
            spinnerListAdapter.addAll(teams)
        }

        // League Entries observer to update menu items
        viewModel.leaguesEntries.observe(viewLifecycleOwner) { leagues ->
            menu?.let { menu ->
                for (league in leagues) {
                    menu.add(league)
                }
            }
        }
        return binding.root
    }

    // Menu implementation
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
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateTeams(item.title as String)

        return true
    }

}
