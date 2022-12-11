package com.delaiglesia.moviesapp.presentation.artist

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
import com.delaiglesia.moviesapp.databinding.ActivityArtistBinding
import com.delaiglesia.moviesapp.presentation.constants.Action
import com.delaiglesia.moviesapp.presentation.di.core.Injector
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var binding: ActivityArtistBinding
    private lateinit var artistAdapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)

        (application as Injector).createArtistSubComponent()
            .inject(this)

        artistViewModel = ViewModelProvider(this, factory)
            .get(ArtistViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.artistRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ArtistActivity)
            artistAdapter = ArtistAdapter()
            binding.artistRecyclerView.adapter = artistAdapter
            displayArtists(Action.GET)
        }
    }

    private fun displayArtists(action: Action) {
        binding.artistProgressBar.visibility = View.VISIBLE

        val responseLiveData = when (action) {
            Action.GET -> artistViewModel.getArtists()
            Action.UPDATE -> artistViewModel.updateArtists()
        }

        responseLiveData.observe(this) { artists ->
            if (artists != null) {
                artistAdapter.setList(artists)
                artistAdapter.notifyDataSetChanged()
                binding.artistProgressBar.visibility = View.GONE
            } else {
                binding.artistProgressBar.visibility = View.GONE
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
                displayArtists(Action.UPDATE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}