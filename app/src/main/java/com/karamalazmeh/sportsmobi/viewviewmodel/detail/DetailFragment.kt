package com.karamalazmeh.sportsmobi.viewviewmodel.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karamalazmeh.sportsmobi.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val sportEvent = DetailFragmentArgs.fromBundle(requireArguments()).selectedEvent

        binding.sportEvent = sportEvent

        return binding.root
    }
}


