class CreditCard(val numbers: String) {
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
            in 51..55 -> Flag.MASTER // parece q master card tem esses intervalos tmb : 2221–2720
            else -> Flag.NOT_DEFINED
        }
        if (numbers[0] == '4') {
            flag = Flag.VISA
        }

        val correctSize: Boolean = when(flag) {
            Flag.VISA -> numbers.length == 13 || numbers.length == 16
            Flag.MASTER -> numbers.length == 16
            Flag.AMEX -> numbers.length == 15
            else -> false
        }
        this.flag = flag
        return correctSize
    }
}

enum class Flag {
    VISA, MASTER, AMEX, ELO, NOT_DEFINED
}