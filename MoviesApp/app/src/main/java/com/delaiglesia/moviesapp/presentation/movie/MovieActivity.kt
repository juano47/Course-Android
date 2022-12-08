package com.delaiglesia.moviesapp.presentation.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.delaiglesia.moviesapp.R
import com.delaiglesia.moviesapp.databinding.ActivityMovieBinding
import com.delaiglesia.moviesapp.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        (application as Injector).createMovieSubComponent()
            .inject(this)

        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MovieActivity)
            movieAdapter = MovieAdapter()
            binding.movieRecyclerView.adapter = movieAdapter
            displayMovies(Action.GET)
        }
    }

    private fun displayMovies(action: Action) {
        binding.movieProgressBar.visibility = View.VISIBLE

        val responseLiveData = when (action) {
            Action.GET -> movieViewModel.getMovies()
            Action.UPDATE -> movieViewModel.updateMovies()
        }

        responseLiveData.observe(this) { movies ->
            if (movies != null) {
                movieAdapter.setList(movies)
                movieAdapter.notifyDataSetChanged()
                binding.movieProgressBar.visibility = View.GONE
            } else {
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG).show()
            }
        }
    }

    //show menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                displayMovies(Action.UPDATE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    enum class Action {
        GET, UPDATE
    }
}