package com.anushka.bindingdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.anushka.bindingdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DataBinding
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //necesario agregar <layout> en el xml!!

        //ViewBinding! no necesita agregar <layout> en el xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            displayGreeting()
        }
    }

    private fun displayGreeting() {
        binding.apply {
            greetingTextView.text = "Hello! " + nameEditText.text
        }

    }
}