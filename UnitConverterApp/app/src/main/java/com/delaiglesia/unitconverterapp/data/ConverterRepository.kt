package com.delaiglesia.unitconverterapp.data

import kotlinx.coroutines.flow.Flow

interface ConverterRepository {
    suspend fun insertConversionResult(conversionResult: ConversionResult)
    suspend fun deleteConversionResult(conversionResult: ConversionResult)
    suspend fun deleteAllConversionResults()
    fun getAllConversionResults(): Flow<List<ConversionResult>>
}