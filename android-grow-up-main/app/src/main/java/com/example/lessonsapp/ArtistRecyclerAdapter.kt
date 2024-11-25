package com.example.lessonsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.imageview.ShapeableImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArtistRecyclerAdapter(
    private val context: Context,
    private var artistList: List<ArtistModel>,
    private val itemClickListener: (ArtistModel) -> Unit
) : RecyclerView.Adapter<ArtistRecyclerAdapter.ArtistViewHolder>() {

    private val originalList = artistList.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_artist, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artistList[position]
        holder.bind(artist, itemClickListener)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistImageView: ShapeableImageView = itemView.findViewById(R.id.ivArtist)
        private val artistNameTextView: TextView = itemView.findViewById(R.id.tvArtistName)

        fun bind(artist: ArtistModel, clickListener: (ArtistModel) -> Unit) {
            artistImageView.setImageResource(artist.imageResId)
            artistNameTextView.text = artist.name
            itemView.setOnClickListener { clickListener(artist) }
        }
    }

    fun updateList(newList: List<ArtistModel>) {
        artistList = newList
        notifyDataSetChanged()
    }

    fun getOriginalList(): List<ArtistModel> {
        return originalList
    }
}