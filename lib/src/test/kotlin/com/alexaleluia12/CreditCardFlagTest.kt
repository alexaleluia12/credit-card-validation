package com.alexaleluia12

import kotlin.test.Test
import kotlin.test.assertEquals

class CreditCardFlagTest {

    @Test
    fun `correct identify Elo Flg` () {
        val card = CreditCard("4514160000000003")
        card.validatePrefixAndSize()
        assertEquals(Flag.ELO, card.flag)
    }
    @Test
    fun `correct identify Visa Flag`() {
        val card = CreditCard("4012888888881881")
        card.validatePrefixAndSize()
        assertEquals(Flag.VISA, card.flag)
    }

    @Test
    fun `correct identify Master Flag`() {
        val card = CreditCard("5212888888881881")
        card.validatePrefixAndSize()
        assertEquals(Flag.MASTER, card.flag)
    }

    @Test
    fun `correct identify Master Flag - edge cases`() {
        val card = CreditCard("5112888888881881")
        card.validatePrefixAndSize()
        assertEquals(Flag.MASTER, card.flag)

        val secondCard = CreditCard("5512888888881881")
        secondCard.validatePrefixAndSize()
        assertEquals(Flag.MASTER, secondCard.flag)
    }

    @Test
    fun `correct identify Amex Flag`() {
        val card = CreditCard("3412888888881881")
        card.validatePrefixAndSize()
        assertEquals(Flag.AMEX, card.flag)

        val secondCard = CreditCard("3712888888881881")
        secondCard.validatePrefixAndSize()
        assertEquals(Flag.AMEX, secondCard.flag)
    }

    @Test
    fun `correct identify unknown Flag`() {
        val card = CreditCard("9912888888881881")
        card.validatePrefixAndSize()
        assertEquals(Flag.NOT_DEFINED, card.flag)
    }

}