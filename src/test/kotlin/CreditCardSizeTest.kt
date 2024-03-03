import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CreditCardSizeTest {

    @Test
    fun `correct size(16) for Visa card`() {
        val card = CreditCard("4012888888881881")
        val resultSize = card.validatePrefixAndSize()
        assertTrue(resultSize)
    }

    @Test
    fun `correct size(13) for Visa card`() {
        val card = CreditCard("4012888881881")
        val resultSize = card.validatePrefixAndSize()
        assertTrue(resultSize)
    }

    @Test
    fun `wrong size for Visa card`() {
        val card = CreditCard("401288888881881")
        val resultSize = card.validatePrefixAndSize()
        assertFalse(resultSize)
    }

    @Test
    fun `correct size(15) for Amex card`() {
        val card = CreditCard("341288888881881")
        val resultSize = card.validatePrefixAndSize()
        assertTrue(resultSize)
        assertEquals(Flag.AMEX, card.flag)
    }

    @Test
    fun `wrong size for Amex card`() {
        val card = CreditCard("34128888881881")
        val resultSize = card.validatePrefixAndSize()
        assertFalse(resultSize)
        assertEquals(Flag.AMEX, card.flag)
    }

    @Test
    fun `correct size(16) for Master card`() {
        val card = CreditCard("5312888888881881")
        val resultSize = card.validatePrefixAndSize()
        assertTrue(resultSize)
        assertEquals(Flag.MASTER, card.flag)
    }

    @Test
    fun `wrong size for Master card`() {
        val card = CreditCard("531288888881881")
        val resultSize = card.validatePrefixAndSize()
        assertFalse(resultSize)
        assertEquals(Flag.MASTER, card.flag)
    }
}