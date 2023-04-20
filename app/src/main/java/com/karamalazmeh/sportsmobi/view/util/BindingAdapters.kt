package com.karamalazmeh.sportsmobi.view.util

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.*
import androidx.core.graphics.ColorUtils
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.karamalazmeh.sportsmobi.R
import com.karamalazmeh.sportsmobi.model.entity.SportEvent
import com.karamalazmeh.sportsmobi.model.entity.Team
import com.karamalazmeh.sportsmobi.model.network.thesportsdbapi.TheSportsDbApiStatus
import com.karamalazmeh.sportsmobi.view.main.MainViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import timber.log.Timber
import java.lang.Exception



@BindingAdapter("scoreText")
fun bindTextViewToScore(textView: TextView, score: Int) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.score), score)
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SportEvent>?) {
    val adapter = recyclerView.adapter as? ResultsListAdapter
    adapter?.submitList(data)
}



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


@BindingAdapter ("pictureUrl")
fun bindPicture(imageView : ImageView, url: String?) {

    if (url != null && url != "") {
        Picasso.get().load(url)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.setBackgroundColor(Color.argb(100, 255, 255, 255))
                    val bitmap = (imageView.drawable as BitmapDrawable).bitmap
                    val palette = Palette.from(bitmap).generate()
                    var dominantColor =
                        palette.getDominantColor(imageView.context.getColor(R.color.white))
                    dominantColor = ColorUtils.setAlphaComponent(dominantColor, 50)
                }

                override fun onError(e: Exception?) {
                    Timber.e(e)
                }

            })
    }
}

