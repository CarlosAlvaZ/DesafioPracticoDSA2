package com.example.desafiopracticoiidsa2.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopracticoiidsa2.Models.Track
import com.example.desafiopracticoiidsa2.databinding.TrackItemLayoutBinding
import com.squareup.picasso.Picasso

class TrackViewHolder(view: View):RecyclerView.ViewHolder(view){

    private val binding = TrackItemLayoutBinding.bind(view)

    fun bind(item: Track) {
        Picasso.get().load(item.album.images[0].url).into(binding.trackImage)
        binding.trackTitle.text = item.name
        binding.trackSubtitle.text = item.artists.joinToString(", ")
    }
}