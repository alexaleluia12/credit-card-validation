package com.alexaleluia12

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CreditCardTest {
    @Test
    fun `valid Amex card`() {
        val card = CreditCard("378282246310005")
        assertTrue(card.isValid)
    }

    @Test
    fun `not valid Amex card`() {
        val card = CreditCard("378282246320005")
        assertFalse(card.isValid)
    }

    @Test
    fun `valid Visa card`() {
        val card = CreditCard("4012888888881881")
        assertTrue(card.isValid)
    }

    @Test
    fun `not valid Visa card`() {
        val card = CreditCard("4012888888882881")
        assertFalse(card.isValid)
    }

    @Test
    fun `valid Master card`() {
        val card = CreditCard("5105105105105100")
        assertTrue(card.isValid)
    }

    @Test
    fun `not valid Master card`() {
        val card = CreditCard("5105105205105100")
        assertFalse(card.isValid)
    }

    @Test
    fun `valid Elo card`() {
        val card = CreditCard("6362971747129170")
        assertTrue(card.isValid)
    }

    @Test
    fun `not valid Elo card`() {
        val card = CreditCard("6362971747129270")
        assertFalse(card.isValid)
    }

    @Test
    fun `clean card input`() {
        val card = CreditCard("6362 9717 4712 9270")
        assertEquals("6362971747129270", card.numbers)
    }
}