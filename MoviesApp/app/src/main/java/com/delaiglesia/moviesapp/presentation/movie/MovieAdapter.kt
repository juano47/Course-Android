package com.delaiglesia.moviesapp.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.delaiglesia.moviesapp.R
import com.delaiglesia.moviesapp.data.model.movie.Movie
import com.delaiglesia.moviesapp.databinding.ListItemBinding

class MovieAdapter(): RecyclerView.Adapter<MyViewHolder>() {
    private val movies = ArrayList<Movie>()
    fun setList(movies: List<Movie>){
        this.movies.clear()
        this.movies.addAll(movies)
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
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.titleTextView.text = movie.title
        binding.descriptionTextView.text = movie.overview
        val posterURL = "https://image.tmdb.org/t/p/w500" + movie.posterPath
        Glide.with(binding.imageView.context)
            .load(posterURL)
            .into(binding.imageView)
    }
}
