package com.karamalazmeh.sportsmobi.view.detail


import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.karamalazmeh.sportsmobi.R
import com.karamalazmeh.sportsmobi.databinding.FragmentDetailBinding
import com.karamalazmeh.sportsmobi.view.util.darkenColor
import timber.log.Timber

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val sportEvent = DetailFragmentArgs.fromBundle(arguments!!).selectedEvent

        binding.sportEvent = sportEvent

        return binding.root
    }
}


