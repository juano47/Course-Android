package com.delaiglesia.moviesapp.presentation.tvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.delaiglesia.moviesapp.R
import com.delaiglesia.moviesapp.data.model.tvshow.TvShow
import com.delaiglesia.moviesapp.databinding.ListItemBinding

class TvShowAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private val tvShows = ArrayList<TvShow>()
    fun setList(tvShows: List<TvShow>){
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
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
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }
}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(tvShow: TvShow) {
        binding.titleTextView.text = tvShow.name
        binding.descriptionTextView.text = tvShow.overview
        val posterURL = "https://image.tmdb.org/t/p/w500" + tvShow.posterPath
        Glide.with(binding.imageView.context)
            .load(posterURL)
            .into(binding.imageView)
    }
}
