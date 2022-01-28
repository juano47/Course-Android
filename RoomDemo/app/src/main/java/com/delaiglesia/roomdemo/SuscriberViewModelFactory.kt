package com.delaiglesia.roomdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delaiglesia.roomdemo.db.SuscriberRepository
import java.lang.IllegalArgumentException

class SuscriberViewModelFactory(private val repository: SuscriberRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuscriberViewModel::class.java)){
            return SuscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}