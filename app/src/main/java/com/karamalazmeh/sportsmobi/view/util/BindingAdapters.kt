package com.karamalazmeh.sportsmobi.view.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karamalazmeh.sportsmobi.R
import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApiStatus

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Int) {
    if (isHazardous == 1) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Int) {
    if (isHazardous == 1) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, venue: String) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), venue)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, venue: String) {
    val context = textView.context
    textView.text = venue
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, venue: String) {
    val context = textView.context
    textView.text = venue
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SportEvent>?) {
    val adapter = recyclerView.adapter as? ResultsListAdapter
    adapter?.submitList(data)
}



@BindingAdapter ("nasaApiStatus")
fun bindStatus(statusImageView: ProgressBar, status: TheSportsDbApiStatus?) {
    when (status) {
        TheSportsDbApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        TheSportsDbApiStatus.ERROR -> {
            statusImageView.visibility =   View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        TheSportsDbApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {}
    }
}