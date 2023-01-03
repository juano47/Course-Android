package com.delaiglesia.unitconverterapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ConversionResult::class], version = 1, exportSchema = false)
abstract class ConverterDatabase: RoomDatabase() {

    abstract val converterDao: ConverterDao

    companion object {
        //Volatile: es una variable que se puede acceder desde cualquier hilo y se actualiza en tiempo real
        @Volatile
        private var INSTANCE: ConverterDatabase? = null
        fun getInstance(context: Context): ConverterDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        ConverterDatabase::class.java,
                        "converter_database"
                    ).build()
                }
                return instance
            }
        }
    }

}