package com.aforce.aniimex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aforce.aniimex.databinding.ItemAnimeBinding
import com.aforce.aniimex.extensions.imageUrl
import com.aforce.aniimex.model.Anime
import java.text.FieldPosition


class AnimeAdapter(private val onAnimeClicked: (Anime) -> Unit) :
    ListAdapter<Anime, AnimeAdapter.ViewHolder>(AnimeItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anime = getItem(position)
        holder.binding.tvName.text = anime.name
        holder.binding.tvDescription.text = anime.description
        holder.binding.ivAnime.imageUrl(anime.url)
        holder.binding.root.setOnClickListener{
            onAnimeClicked(anime)
        }
    }

    inner class ViewHolder(val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root)
}

class AnimeItemCallBack : DiffUtil.ItemCallback<Anime>() {
    override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem.name == newItem.name
    }

}