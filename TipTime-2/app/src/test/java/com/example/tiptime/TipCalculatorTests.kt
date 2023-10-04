package com.example.tiptime
import org.junit.Test
import org.junit.Assert.assertEquals
import java.text.NumberFormat

/**
 * Created by Batista Silva on 03/10/2023.
 */
class TipCalculatorTests {
    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        println("TipCalculatorTests...!!!")
        assertEquals(expectedTip, actualTip)
    }
}
