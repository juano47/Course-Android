package com.delaiglesia.roomdemo.db

class SuscriberRepository(private val dao : SuscriberDAO) {

    val subscribers = dao.getAllSuscribers()

    suspend fun insert(suscriber: Suscriber){
        dao.insertSuscriber(suscriber)
    }

    suspend fun update(suscriber: Suscriber){
        dao.updateSuscriber(suscriber)
    }

    suspend fun delete(suscriber: Suscriber){
        dao.deleteSuscriber(suscriber)
    }

    suspend fun deleteAllSuscribers(){
        dao.deleteAll()
    }


}