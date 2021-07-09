package com.github.isactomaz.bin2decandroid.utils

import org.junit.Assert.*


import org.junit.Test

class ConvertNumberTest {

    @Test
    fun shouldConvertNumberAnswer15With1111First() {
        val actual = convertNumber("1111")
        assertEquals("15", actual.first)
    }

    @Test
    fun shouldConvertNumberAnswerFalseWith1111Second() {
        val actual = convertNumber("1111")
        assertFalse(actual.second)
    }

    @Test
    fun shouldConvertNumberAnswerErrorWith1O01First() {
        val actual = convertNumber("1O01")
        assertEquals("Error", actual.first)
    }

    @Test
    fun shouldConvertNumberAnswerTrueWith1O01Second() {
        val actual = convertNumber("1O01")
        assertTrue(actual.second)
    }

}