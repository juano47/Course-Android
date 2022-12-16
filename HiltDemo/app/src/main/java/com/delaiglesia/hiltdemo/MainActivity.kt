package com.delaiglesia.hiltdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delaiglesia.hiltdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var data = dataSource.getRemoteData()
        binding.textView.text = data
    }
}