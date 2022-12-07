package com.delaiglesia.moviesapp.presentation.tvShow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.delaiglesia.moviesapp.R
import com.delaiglesia.moviesapp.databinding.ActivityTvShowBinding

class TvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
    }
}