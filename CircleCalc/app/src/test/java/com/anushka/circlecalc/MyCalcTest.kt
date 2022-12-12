package com.anushka.circlecalc

import org.junit.Assert.*
import org.junit.Test

class MyCalcTest {

    private val myCalc = MyCalc()

    @Test
    fun calculateCircumference() {
        val result = myCalc.calculateCircumference(2.0)
        assertEquals(12.56, result, 0.01)
    }

    @Test
    fun calculateArea() {
        val result = myCalc.calculateArea(2.0)
        assertEquals(12.56, result, 0.01)
    }
}