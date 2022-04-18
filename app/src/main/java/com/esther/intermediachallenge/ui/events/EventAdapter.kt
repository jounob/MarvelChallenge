package com.esther.intermediachallenge.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esther.intermediachallenge.R
import com.esther.intermediachallenge.data.models.Events
import com.esther.intermediachallenge.databinding.EventsItemBinding
import com.esther.intermediachallenge.ui.base.BaseAdapter
import com.esther.intermediachallenge.utils.binding.setImage

class EventAdapter : BaseAdapter<Events,
        EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            EventsItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.events_item, parent, false
                )
            ), onItemClick = onClickListener
        )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class EventViewHolder(
        private val binding: EventsItemBinding,
        private val onItemClick: ((Events) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(events: Events) {
            binding.apply {
                setImage(ivEventsThumbnail, events.thumbnail)
                tvTitleEvent.text = events.title
                tvDateEvent.text = events.start ?: "no date"
                imBtnArrow.setOnClickListener {
                    onItemClick?.invoke(events)
                }
            }
        }
    }

}