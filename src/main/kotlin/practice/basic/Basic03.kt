package practice.basic

/**
 *
 *  Dealing with types
 *
 * **/

fun explicitType() {
    val number1 = 10
    val number2: Long = number1.toLong()

    // number3 is double type
    val number3 = number1 / number2.toDouble()

    // handle nullable variable
    val number4: Int? = 3
    val number5: Long = number4?.toLong() ?: 0L
}

//Unit is equal to void or Void in java.
//Unit is a singleton instance
//Any is equal to Object in java
fun typeCasting(obj: Any) {
    //smart cast -> java 16 or later also support it via pattern matching
    if (obj is String) {
        val str = obj as String
        println(str.length)
    }

    // opposite of instanceof
    if (obj !is String) {
        println("not a string")
    }

    //if obj is not string then class cast exception
    val str1 = obj as String
    //nullable
    val str = obj as? String
    println(str?.length)
}

//Nothing
//function never returns normal value
fun exception(): Nothing {
    throw RuntimeException()
}

//String interpolation / indexing
// ${variable} or $variable
fun stringPractice() {
    val name = "John"
    println("Hello, ${name}")
    println("Hello, $name")

    val J = name[0]
    val o = name[1]
    val h = name[2]
    val n = name[3]
}
