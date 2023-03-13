package com.aboda.basesystemcalculator

import kotlin.math.pow

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

    fun decimalToOct(decimalValue: String): String {
        var longDecimalValue = decimalValue.toLong()
        var myOctValue  = ""
        while (longDecimalValue > 0) {
            myOctValue = (longDecimalValue % 8).toString().plus(myOctValue)
            longDecimalValue /= 8
        }
        return myOctValue
    }

    fun binaryToDecimal(binaryValue: String): String {
        var longDecimalValue = 0L
        var i = 0.0
        var myBinaryValue = binaryValue
        while (myBinaryValue.length > 0) {
            longDecimalValue += (2.0.pow(i).toLong() *
                    myBinaryValue.substring(myBinaryValue.length - 1).toLong())
            myBinaryValue = myBinaryValue.substring(0, myBinaryValue.length - 1)
            i++
        }
        return longDecimalValue.toString()
    }

    fun octToDecimal(octValue: String): String {
        var longDecimalValue = 0L
        var i = 0.0
        var myOctValue = octValue
        while (myOctValue.length > 0) {
            longDecimalValue += (8.0.pow(i).toLong() *
                    myOctValue.substring(myOctValue.length - 1).toLong())
            myOctValue = myOctValue.substring(0, myOctValue.length - 1)
            i++
        }
        return longDecimalValue.toString()
    }

    fun hexToDecimal(hexValue: String): String {
        return hexValue.toLong(16).toString()
    }
}