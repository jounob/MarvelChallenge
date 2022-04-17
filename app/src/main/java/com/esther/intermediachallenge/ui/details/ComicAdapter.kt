package com.esther.intermediachallenge.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esther.intermediachallenge.R
import com.esther.intermediachallenge.data.models.Comic
import com.esther.intermediachallenge.databinding.ItemComicsBinding
import com.esther.intermediachallenge.ui.base.BaseAdapter

class ComicAdapter : BaseAdapter<Comic,
        ComicAdapter.ComicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder =
        ComicsViewHolder(
            ItemComicsBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_comics,
                    parent,
                    false
                )
            ),
        )

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ComicsViewHolder(private val binding: ItemComicsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic) {
            binding.apply {
                tvComicTitle.text = comic.title
                tvComicYear.text = comic.dates[0].date.substringBefore("-")

            }
        }
    }
}
