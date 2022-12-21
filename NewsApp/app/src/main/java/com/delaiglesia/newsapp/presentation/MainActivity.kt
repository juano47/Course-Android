package com.delaiglesia.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.delaiglesia.newsapp.R
import com.delaiglesia.newsapp.databinding.ActivityMainBinding
import com.delaiglesia.newsapp.presentation.adapter.NewsAdapter
import com.delaiglesia.newsapp.presentation.viewModel.NewsViewModel
import com.delaiglesia.newsapp.presentation.viewModel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory
    @Inject
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        val navController = navHostFragment?.findNavController()
        binding.bnvNews.setupWithNavController(navController!!)

        viewModel = ViewModelProvider(this, factory).get(NewsViewModel::class.java)
    }
}