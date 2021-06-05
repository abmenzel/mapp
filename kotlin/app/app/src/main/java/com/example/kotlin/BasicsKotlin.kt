package com.example.kotlin

class BasicsKotlin {

    // 1. Kotlin variables, immutable/mutable
    var mutable = "mutable"
    val immutable = "immutable"

    // 2. Kotlin type inference
    var mutableString = "mutable" // --> String
    var mutableString2 : String = "mutable" // --> String

    // 3. Kotlin string formatting
    val number = 30;
    val format1 = "number is $number";
}