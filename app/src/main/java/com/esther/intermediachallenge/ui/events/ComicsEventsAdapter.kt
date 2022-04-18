package com.esther.intermediachallenge.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esther.intermediachallenge.R
import com.esther.intermediachallenge.data.models.Comic
import com.esther.intermediachallenge.databinding.ItemEventComicsBinding
import com.esther.intermediachallenge.ui.base.BaseAdapter


class ComicsEventsAdapter : BaseAdapter<Comic,
        ComicsEventsAdapter.ComicsEVentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsEVentViewHolder =
        ComicsEVentViewHolder(
            ItemEventComicsBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_event_comics,
                    parent,
                    false
                )
            )
        )

    override fun onBindViewHolder(holder: ComicsEVentViewHolder, position: Int) {
        holder.bind(list[position])
    }


    class ComicsEVentViewHolder(private val binding: ItemEventComicsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic) {
            binding.apply {
                tvComicTitle.text = comic.title
                tvComicYear.text = comic.dates[0].date.substringBefore("-")
            }
        }
    }


}