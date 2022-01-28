package com.delaiglesia.roomdemo

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delaiglesia.roomdemo.db.Suscriber
import com.delaiglesia.roomdemo.db.SuscriberRepository
import kotlinx.coroutines.launch

class SuscriberViewModel(private val repository: SuscriberRepository) : ViewModel(), Observable {

    val suscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var suscriberToUpdateOrDelete : Suscriber

    @Bindable
    val inputName = MutableLiveData<String?>()
    @Bindable
    val inputEmail = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()

    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear all"
        inputName.value = ""
        inputEmail.value = ""
    }

    fun reset() {
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear all"
        inputName.value = ""
        inputEmail.value = ""
    }

    fun saveOrUpdate() {
        val name = inputName.value!!
        val email = inputEmail.value!!
        if (isUpdateOrDelete){
            update(Suscriber(suscriberToUpdateOrDelete.id, name, email))
        }else{
            insert(Suscriber(0, name, email))
        }
        inputName.value = ""
        inputEmail.value = ""

    }

    fun clearAllOrDelete(){
        if (isUpdateOrDelete){
            delete(suscriberToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun insert(suscriber: Suscriber){
        viewModelScope.launch {
            repository.insert(suscriber)
        }
    }

    fun update(suscriber: Suscriber){
        viewModelScope.launch {
            repository.update(suscriber)
        }
        reset()
    }

    fun delete(suscriber: Suscriber){
        viewModelScope.launch {
            repository.delete(suscriber)
        }
        reset()
    }

    fun clearAll(){
        viewModelScope.launch {
            repository.deleteAllSuscribers()
        }
    }

    fun initUpdateAndDelete(suscriber: Suscriber){
        inputName.value = suscriber.name
        inputEmail.value = suscriber.email
        isUpdateOrDelete = true
        suscriberToUpdateOrDelete = suscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Remove"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

}