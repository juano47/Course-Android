package com.delaiglesia.unitconverterapp.data

import java.io.Serializable

//data class is a class that is used to hold data and it is used to create objects
//that hold data only and do not have any other functionality.
data class Conversion(
    val id: Int,
    val description: String,
    val convertFrom: String,
    val convertTo: String,
    val conversionFactor: Double
) : Serializable