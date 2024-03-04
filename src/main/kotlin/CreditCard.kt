import kotlin.text.Regex
class CreditCard(numbersCard: String) {
    val numbers = numbersCard.replace(" ", "")

    var isValid: Boolean  = false
        get() {
            return validatePrefixAndSize() && validateLunchAlgorithm()
        }
    var flag = Flag.NOT_DEFINED

    fun validateLunchAlgorithm(): Boolean {
        val intNumbers = numbers.map { it.digitToInt() }
        val doubledNumbers = MutableList<Int>(intNumbers.size) { 0 }
        var count = 1
        for (i in intNumbers.lastIndex downTo  0 ) {
            if (count % 2 == 0) {
                doubledNumbers[i] = intNumbers[i] * 2
                count = 0
            } else {
                doubledNumbers[i] = intNumbers[i]
            }
            count += 1
        }

        val subNumbers = MutableList<Int>(doubledNumbers.size) { 0 }
        for (i in doubledNumbers.indices) {
            var n = doubledNumbers[i]
            if (n > 9) {
                n -= 9
            }
            subNumbers[i] = n
        }
        return subNumbers.sum() % 10 == 0
    }

    fun validatePrefixAndSize(): Boolean {
        if (numbers.length <= 2) {
            return false
        }
        val firstTowDigits = numbers.slice(0..< 2).toInt()
        var flag: Flag = when (firstTowDigits) {
            34, 37 -> Flag.AMEX
            in 51..55 -> Flag.MASTER
            else -> Flag.NOT_DEFINED
        }
        if (verifyEloPrefix()) {
            flag = Flag.ELO
        } else if(numbers[0] == '4') {
            flag = Flag.VISA
        }

        val correctSize: Boolean = when(flag) {
            Flag.VISA -> numbers.length == 13 || numbers.length == 16
            Flag.MASTER -> numbers.length == 16
            Flag.AMEX -> numbers.length == 15
            Flag.ELO -> numbers.length == 16
            else -> false
        }
        this.flag = flag
        return correctSize
    }

    private fun verifyEloPrefix(): Boolean {
        val startOptions = listOf(
            "^50",
            "^6[235]",
            "^4011(78|79)",
            "^43(1274|8935)",
            "^45(1416|7393|763(1|2))"
        )
        val regexPattern = Regex(startOptions.joinToString("|"))
        val result = regexPattern.find(numbers)
        return result != null
    }
}

enum class Flag {
    VISA, MASTER, AMEX, ELO, NOT_DEFINED
}