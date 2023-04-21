package com.karamalazmeh.sportsmobi.viewviewmodel.util

import android.graphics.Color
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karamalazmeh.sportsmobi.R
import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApiStatus
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import timber.log.Timber


// bind score to textView
@BindingAdapter("scoreText")
fun bindTextViewToScore(textView: TextView, score: Int) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.score), score)
}

// bind recycler view data
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SportEvent>?) {
    val adapter = recyclerView.adapter as? ResultsListAdapter
    adapter?.submitList(data)
}


// bind status loading and error connection image
@BindingAdapter ("apiStatus")
fun bindStatus(statusImageView: ImageView, status: TheSportsDbApiStatus?) {
    when (status) {
        TheSportsDbApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        TheSportsDbApiStatus.ERROR -> {
            statusImageView.visibility =   View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        TheSportsDbApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {}
    }
}

// bind pictures from internet
@BindingAdapter ("pictureUrl")
fun bindPicture(imageView : ImageView, url: String?) {

    if (url != null && url != "") {
        Picasso.get().load(url)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.setBackgroundColor(Color.argb(100, 255, 255, 255))
                }
                override fun onError(e: Exception?) {
                    Timber.e(e)
                }
            })
    }
}

