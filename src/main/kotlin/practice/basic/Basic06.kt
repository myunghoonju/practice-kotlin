package practice.basic

// kotlin uses arithmetic sequence
fun main() {
    // 1~10
    for (i in 1..10) {
        println(i)
    }

    //10~1
    for (i in 10 downTo 1) {
        println(i)
    }

    //1 3 5 ..
    for (i in 1..10 step 2) {
        println(i)
    }

    //while
    var i = 1
    while(i <= 10) {
        print(i)
        i++
    }
}