package com.delaiglesia.unitconverterapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConverterDao {
    @Insert
    suspend fun insertConversionResult(conversionResult: ConversionResult)

    @Delete
    suspend fun deleteConversionResult(conversionResult: ConversionResult)

    //delete all conversion results
    @Query("DELETE FROM conversion_result")
    suspend fun deleteAllConversionResults()

    //no es necesario suspend porque room ya lo hace por nosotros en segundo plano
    @Query("SELECT * FROM conversion_result")
    fun getAllConversionResults(): Flow<List<ConversionResult>>
    //Flow es un tipo de dato que nos permite observar cambios en la base de datos
    //y reaccionar a ellos en tiempo real (como un observable)
}