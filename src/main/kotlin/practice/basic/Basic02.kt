package practice.basic

/**
 *
 *  Dealing with null
 *
 * */

fun prac01(str: String?): Boolean { // boolean in java
    if (str == null) {
        throw IllegalArgumentException("String cannot be null")
    }

    return str.startsWith("A")
}

fun prac01More(str: String?): Boolean {
   return str?.startsWith("A") ?: throw IllegalArgumentException("String cannot be null")
}

fun prac02(str: String?): Boolean? { // Boolean in java
    if (str == null) {
        return null
    }

    return str.startsWith("A")
}

fun prac02More(str: String?): Boolean? {
    return str?.startsWith("A")
}

fun prac03(str: String?): Boolean {
//    return str.startsWith("A") -> compile error coz of nullability
    if (str == null) { // null checked
        return false
    }

    return str.startsWith("A") // never null
}

fun prac03More(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}

fun prac04(str: String?): Boolean {
    //mark never null
    //caution: it will occur null pointer exception
    return str!!.startsWith("A")
}