package com.delaiglesia.unitconverterapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversion_result")
data class ConversionResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val messagePart1: String,
    val messagePart2: String,
)