package practice

fun main() {
    var number: Int = 10
    val finalNumber: Int = 10
    number = 11
// compile error
// finalNumber = 12

// compiler decide which is Long or long
    var number01: Long?
//    compile error
//    println(number01)
    number01 = null
    println(number01)

// assign value only once (java final)
    val number02: Long
    number02 = 12L
}