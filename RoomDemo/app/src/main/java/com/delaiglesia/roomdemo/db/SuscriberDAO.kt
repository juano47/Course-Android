package com.delaiglesia.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SuscriberDAO {

    //suspend modifier is include in room and it's a implementation of a coroutine
    //Room task can't be running in main thread
    @Insert
    suspend fun insertSuscriber(suscriber: Suscriber): Long

    @Update
    suspend fun updateSuscriber(suscriber: Suscriber)

    @Delete
    suspend fun deleteSuscriber(suscriber: Suscriber)

    @Query("DELETE FROM suscriber_data_table")
    suspend fun deleteAll()

    //when a function return a LiveData isn't necessary use suspend, it's execute
    //in background for default
    @Query("SELECT * FROM suscriber_data_table")
    fun getAllSuscribers(): LiveData<List<Suscriber>>
}