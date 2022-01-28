package com.delaiglesia.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suscriber_data_table")
data class Suscriber (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "suscriber_id")
    val id: Int,
    val name: String,
    val email: String


)