package com.delaiglesia.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.delaiglesia.roomdemo.databinding.ActivityMainBinding
import com.delaiglesia.roomdemo.db.Suscriber
import com.delaiglesia.roomdemo.db.SuscriberDatabase
import com.delaiglesia.roomdemo.db.SuscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SuscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SuscriberDatabase.getInstance(application).suscriberDAO
        val repository = SuscriberRepository(dao)
        val factory = SuscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SuscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.suscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySuscribersList()
    }

    private fun displaySuscribersList(){
        subscriberViewModel.suscribers.observe(this, Observer {
            Log.i("MYTAG", it.toString())
            binding.suscriberRecyclerView.adapter = MyRecyclerViewAdapter(it, {selectedItem: Suscriber -> listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(suscriber: Suscriber){
        subscriberViewModel.initUpdateAndDelete(suscriber)
    }

}