package com.example.desafiopracticoiidsa2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopracticoiidsa2.Models.Track
import com.example.desafiopracticoiidsa2.viewHolders.TrackViewHolder
import com.example.desafiopracticoiidsa2.R

class TrackAdapter(val items:List<Track>) : RecyclerView.Adapter<TrackViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TrackViewHolder(layoutInflater.inflate(R.layout.track_item_layout, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}