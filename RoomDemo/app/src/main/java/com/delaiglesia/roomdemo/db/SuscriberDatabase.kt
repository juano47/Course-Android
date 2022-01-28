package com.delaiglesia.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Suscriber::class], version = 1)
abstract class SuscriberDatabase : RoomDatabase() {

    abstract val suscriberDAO : SuscriberDAO

    //Singleton
    companion object {
        @Volatile
        private var INSTANCE : SuscriberDatabase? = null
        fun getInstance(context: Context):SuscriberDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SuscriberDatabase::class.java,
                        "suscriber_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}