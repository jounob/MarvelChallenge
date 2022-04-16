package com.esther.intermediachallenge.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esther.intermediachallenge.R
import com.esther.intermediachallenge.data.models.Character
import com.esther.intermediachallenge.databinding.ViewHeroItemBinding
import com.esther.intermediachallenge.ui.base.BaseAdapter
import com.esther.intermediachallenge.utils.binding.setImage

class CharactersAdapter : BaseAdapter<Character,
        CharactersAdapter.CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder(
            ViewHeroItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_hero_item,
                    parent,
                    false
                )
            ), onItemClick = onClickListener
        )

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class CharactersViewHolder(
        private val binding: ViewHeroItemBinding,
        private val onItemClick: ((Character) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                setImage(imageCharacterThumbnail, character.thumbnail)
                characterName.text = character.name
                characterDescription.text = character.description
                root.setOnClickListener {
                    onItemClick?.invoke(character)
                }
            }
        }
    }
}