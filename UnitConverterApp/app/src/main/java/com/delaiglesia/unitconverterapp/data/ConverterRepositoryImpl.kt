package com.delaiglesia.unitconverterapp.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class ConverterRepositoryImpl(private val converterDao: ConverterDao): ConverterRepository {
    override suspend fun insertConversionResult(conversionResult: ConversionResult) {
        converterDao.insertConversionResult(conversionResult)
    }

    override suspend fun deleteConversionResult(conversionResult: ConversionResult) {
        converterDao.deleteConversionResult(conversionResult)
    }

    override suspend fun deleteAllConversionResults() {
        converterDao.deleteAllConversionResults()
    }

    override fun getAllConversionResults(): Flow<List<ConversionResult>> {
        return converterDao.getAllConversionResults()
    }
}