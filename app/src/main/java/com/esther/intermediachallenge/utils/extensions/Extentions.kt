package com.esther.intermediachallenge.utils.extensions

import java.util.regex.Pattern

val EMAIL_PATTERN =
    "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" +
            "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
            "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
            "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
            "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|" +
            "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"

fun isEmailValid(email: String): Boolean {
    return Pattern.compile(EMAIL_PATTERN)
        .matcher(email).matches()
}

val PASSWORD_PATTERN =
    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
            "(?=.*[a-zA-Z])(?=.*[@#$%^&+=])" +
            "(?=\\S+$).{8,}$"

fun isPasswordValid(password: String): Boolean{
    return Pattern.compile(PASSWORD_PATTERN)
        .matcher(password).matches()
}



//fun isPasswordValid(password: String): Boolean {
//    val PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[a-zA-Z]).{8,}\$"
//    val pattern = Pattern.compile(PASSWORD_PATTERN)
//    val matcher = pattern.matcher(password)
//
//    return matcher.matches()
//}


//val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
//    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//            "\\@" +
//            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//            "(" +
//            "\\." +
//            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//            ")+"
//)
//fun isEmailValid(email: String): Boolean {
//    return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
//}