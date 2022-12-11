package com.delaiglesia.moviesapp.presentation.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.delaiglesia.moviesapp.R
import com.delaiglesia.moviesapp.data.model.artist.Artist
import com.delaiglesia.moviesapp.databinding.ListItemBinding

class ArtistAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private val artists = ArrayList<Artist>()
    fun setList(artists: List<Artist>){
        this.artists.clear()
        this.artists.addAll(artists)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
         val binding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.list_item,
                parent,
                false
            ) as ListItemBinding
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(artists[position])
    }

    override fun getItemCount(): Int {
        return artists.size
    }
}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(artist: Artist) {
        binding.titleTextView.text = artist.name
        binding.descriptionTextView.text =
            "Popularity score: " + artist.popularity.toString().substringBefore(".")
        val posterURL = "https://image.tmdb.org/t/p/w500" + artist.profilePath
        Glide.with(binding.imageView.context)
            .load(posterURL)
            .into(binding.imageView)
    }
}
