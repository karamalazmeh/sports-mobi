package com.karamalazmeh.sportsmobi.viewviewmodel.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karamalazmeh.sportsmobi.databinding.ListItemBinding
import com.karamalazmeh.sportsmobi.model.entity.SportEvent


class ResultsListAdapter (private val onClickListener : OnClickListener) : ListAdapter<SportEvent, ResultsListAdapter.ResultViewHolder>(
    DiffCallback
) {
    class ResultViewHolder(private var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(sportEvent: SportEvent) {
            binding.result = sportEvent
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val resultViewHolder = ResultViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
        resultViewHolder.itemView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return resultViewHolder
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(result)
        }

        holder.bind(result)

    }

    companion object DiffCallback : DiffUtil.ItemCallback<SportEvent>() {
        override fun areItemsTheSame(oldItem: SportEvent, newItem: SportEvent): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SportEvent, newItem: SportEvent): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (Asteroid: SportEvent) -> Unit) {
        fun onClick(sportEvent: SportEvent) = clickListener(sportEvent)
    }

}
