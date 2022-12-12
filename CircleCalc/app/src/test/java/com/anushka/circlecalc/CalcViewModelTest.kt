package com.anushka.circlecalc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CalcViewModelTest {

    private lateinit var calcViewModel: CalcViewModel
    private lateinit var calculations: Calculations

    /*
     This rule makes sure that the LiveData is updated synchronously in the test thread instead
     of the background thread used by LiveData normally. This is needed to make sure that
     the LiveData is updated before the test asserts the value.
     Without this rule, the test will fail. This rule is needed only for testing LiveData.
     If you are not testing LiveData, you can remove this rule.
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        calculations = Mockito.mock(Calculations::class.java)
        Mockito.`when`(calculations.calculateArea(2.0)).thenReturn(12.56)
        Mockito.`when`(calculations.calculateCircumference(2.0)).thenReturn(12.56)

        calcViewModel = CalcViewModel(calculations)
    }


    @Test
    fun calculateArea() {
        calcViewModel.calculateArea(2.0)
        assertEquals("12.56", calcViewModel.area.value)
    }

    @Test
    fun calculateCircumference() {
        calcViewModel.calculateCircumference(2.0)
        assertEquals("12.56", calcViewModel.circumference.value)
    }
}