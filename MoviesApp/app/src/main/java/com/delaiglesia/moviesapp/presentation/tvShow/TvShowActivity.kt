package com.delaiglesia.moviesapp.presentation.tvShow

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
import com.delaiglesia.moviesapp.databinding.ActivityTvShowBinding
import com.delaiglesia.moviesapp.presentation.constants.Action
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvShowActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var binding: ActivityTvShowBinding
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)

        tvShowViewModel = ViewModelProvider(this, factory)
            .get(TvShowViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.tvShowRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TvShowActivity)
            tvShowAdapter = TvShowAdapter()
            binding.tvShowRecyclerView.adapter = tvShowAdapter
            displayTvShows(Action.GET)
        }
    }

    private fun displayTvShows(action: Action) {
        binding.tvShowProgressBar.visibility = View.VISIBLE

        val responseLiveData = when (action) {
            Action.GET -> tvShowViewModel.getTvShows()
            Action.UPDATE -> tvShowViewModel.updateTvShows()
        }

        responseLiveData.observe(this) { tvShows ->
            if (tvShows != null) {
                tvShowAdapter.setList(tvShows)
                tvShowAdapter.notifyDataSetChanged()
                binding.tvShowProgressBar.visibility = View.GONE
            } else {
                binding.tvShowProgressBar.visibility = View.GONE
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
                displayTvShows(Action.UPDATE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}