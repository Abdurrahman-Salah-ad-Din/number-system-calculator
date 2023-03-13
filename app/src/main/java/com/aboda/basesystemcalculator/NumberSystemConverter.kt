package com.aboda.basesystemcalculator

class NumberSystemConverter {

    fun decimalToBin(decimalValue: String): String {
        var longDecimalValue = decimalValue.toLong()
        var myBinaryValue  = ""
        while (longDecimalValue > 0) {
            myBinaryValue = (longDecimalValue % 2).toString().plus(myBinaryValue)
            longDecimalValue /= 2
        }
        return myBinaryValue
    }

    fun decimalToHex(decimalValue: String): String {
        return java.lang.Long.toHexString(decimalValue.toLong())
    }


}