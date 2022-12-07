package com.delaiglesia.moviesapp.presentation.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.delaiglesia.moviesapp.R
import com.delaiglesia.moviesapp.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
    }
}