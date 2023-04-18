package com.karamalazmeh.sportsmobi.view.main

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.karamalazmeh.sportsmobi.R
import com.karamalazmeh.sportsmobi.databinding.FragmentMainBinding
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.ResultsApiFilter
import com.karamalazmeh.sportsmobi.view.util.ResultsListAdapter
import timber.log.Timber

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.resultsRecycler.adapter = ResultsListAdapter(ResultsListAdapter.OnClickListener {
            viewModel.displayEventResultsDetails(it)
        })

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
                Timber.i(position.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
                Timber.i("nothing selected")
            }
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        viewModel.teamsEntries.observe(viewLifecycleOwner) {
            if (dataAdapter.isEmpty)
            dataAdapter.addAll(it) }

        setHasOptionsMenu(true)

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_week_menu -> ResultsApiFilter.SHOW_WEEK
                R.id.show_today_menu -> ResultsApiFilter.SHOW_TODAY
                else -> ResultsApiFilter.SHOW_ALL
            }
        )
        return true
    }

}
